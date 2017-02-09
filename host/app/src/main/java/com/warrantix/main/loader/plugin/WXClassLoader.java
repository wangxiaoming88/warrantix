package com.warrantix.main.loader.plugin;

import java.io.File;
import java.util.HashMap;

import android.text.TextUtils;

import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.loader.plugin.model.FileSpec;
import com.warrantix.main.loader.plugin.model.SiteSpec;

import dalvik.system.DexClassLoader;

/**
 *
 * Deprecated : Unused Source code
 *
 */
public class WXClassLoader extends DexClassLoader {
	FileSpec file;
	WXClassLoader[] deps;

	WXClassLoader(FileSpec file, String dexPath, String optimizedDirectory,
			String libraryPath, ClassLoader parent, WXClassLoader[] deps) {
		super(dexPath, optimizedDirectory, libraryPath, parent);
		this.file = file;
		this.deps = deps;
	}

	@Override
	protected Class<?> loadClass(String className, boolean resolve)
			throws ClassNotFoundException {
		Class<?> clazz = findLoadedClass(className);
		if (clazz != null)
			return clazz;
		try {
			clazz = getParent().loadClass(className);
		} catch (ClassNotFoundException e) {
		}
		if (clazz != null)
			return clazz;
		if (deps != null) {
			for (WXClassLoader c : deps) {
				try {
					clazz = c.findClass(className);
					break;
				} catch (ClassNotFoundException e) {
				}
			}
		}
		if (clazz != null)
			return clazz;
		clazz = findClass(className);
		return clazz;
	}

	static final HashMap<String, WXClassLoader> loaders = new HashMap<String, WXClassLoader>();

	/**
	 * return null if not available on the disk
	 */
	public static WXClassLoader getClassLoader(SiteSpec site, FileSpec file) {
		WXClassLoader cl = loaders.get(file.id());
		if (cl != null)
			return cl;
		String[] deps = file.deps();
		WXClassLoader[] ps = null;
		if (deps != null) {
			ps = new WXClassLoader[deps.length];
			for (int i = 0; i < deps.length; i++) {
				FileSpec pf = site.getFile(deps[i]);
				if (pf == null)
					return null;
				WXClassLoader l = getClassLoader(site, pf);
				if (l == null)
					return null;
				ps[i] = l;
			}
		}
		File dir = WarrantixApplication.getInstance().getFilesDir();
		dir = new File(dir, "repo");
		if (!dir.isDirectory())
			return null;
		dir = new File(dir, file.id());
		File path = new File(dir, TextUtils.isEmpty(file.md5()) ? "1.apk"
				: file.md5() + ".apk");
		if (!path.isFile())
			return null;
		File outdir = new File(dir, "dexout");
		outdir.mkdir();
		cl = new WXClassLoader(file, path.getAbsolutePath(),
				outdir.getAbsolutePath(), null, WarrantixApplication.getInstance()
						.getClassLoader(), ps);
		loaders.put(file.id(), cl);
		return cl;
	}
}
