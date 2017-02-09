package com.warrantix.main.loader.plugin;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.loader.plugin.model.FileSpec;
import com.warrantix.main.loader.plugin.model.SiteSpec;

public class WXResources {

	FileSpec file;
	String packageName;
	Resources res;
	AssetManager asset;
	WXResources[] deps;

	WXResources(FileSpec file, String packageName, Resources res,
			AssetManager asset, WXResources[] deps) {
		this.file = file;
		this.packageName = packageName;
		this.res = res;
		this.asset = asset;
		this.deps = deps;
	}

	/**
	 * Resources.getDrawable(id)
	 */
	public Drawable getDrawable(int id) {
		return res.getDrawable(id);
	}

	/**
	 * Resources.getText(id)
	 */
	public CharSequence getText(int id) {
		return res.getText(id);
	}

	/**
	 * Resources.getString(id)
	 */
	public String getString(int id) {
		return res.getString(id);
	}

	/**
	 * Resources.getStringArray(id)
	 */
	public String[] getStringArray(int id) {
		return res.getStringArray(id);
	}

	/**
	 * Resources.getColor(id)
	 */
	public int getColor(int id) {
		return res.getColor(id);
	}

	/**
	 * Resources.getColorStateList(id)
	 */
	public ColorStateList getColorStateList(int id) {
		return res.getColorStateList(id);
	}

	/**
	 * Resources.getDimension(id)
	 */
	public float getDimension(int id) {
		return res.getDimension(id);
	}

	/**
	 * Resources.getDimensionPixelSize(id)
	 */
	public int getDimensionPixelSize(int id) {
		return res.getDimensionPixelSize(id);
	}

	/**
	 * Resources.getDimensionPixelOffset(id)
	 */
	public int getDimensionPixelOffset(int id) {
		return res.getDimensionPixelOffset(id);
	}

	/**
	 * Resources.openRawResource(id)
	 */
	public InputStream openRawResource(int id) {
		return res.openRawResource(id);
	}

	public byte[] getRawResource(int id) {
		InputStream ins = openRawResource(id);
		try {
			int n = ins.available();
			ByteArrayOutputStream bos = new ByteArrayOutputStream(n > 0 ? n
					: 4096);
			byte[] buf = new byte[4096];
			int l;
			while ((l = ins.read(buf)) != -1) {
				bos.write(buf, 0, l);
			}
			ins.close();
			return bos.toByteArray();
		} catch (Exception e) {
			return new byte[0];
		}
	}

	/**
	 * 返回独立的Resources
	 * <p>
	 * 对Resources进行操作时不会处理依赖关系，所有依赖包的内容均不会出现在该Resources中。
	 * 
	 * @return
	 */
	public Resources getResources() {
		return res;
	}

	/**
	 * 返回独立的AssetManager
	 * <p>
	 * 对AssetManager进行操作时不会处理依赖关系，所有依赖包的内容均不会出现在该AssetManager中。
	 * 
	 * @return
	 */
	public AssetManager getAssets() {
		return asset;
	}

	/**
	 * 同LayoutInflater.inflate(id, parent, attachToRoot)
	 * <p>
	 * 不会处理依赖关系，请确保id对应的layout在当前包内
	 * 
	 * @param name
	 * @return
	 * @throws Resources.NotFoundException
	 */
	public View inflate(Context context, int id, ViewGroup parent,
			boolean attachToRoot) {
		if (!(context instanceof PluginContainerActivity)) {
			throw new RuntimeException(
					"unable to inflate without MainActivity context");
		}
		PluginContainerActivity ma = (PluginContainerActivity) context;
		WXResources old = ma.getOverrideResources();
		ma.setOverrideResources(this);
		View v = null;
		try {
			v = LayoutInflater.from(context).inflate(id, parent,
					attachToRoot);
			return v;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ma.setOverrideResources(old);
		}

		return v;
	}

	static final HashMap<String, WXResources> loaders = new HashMap<String, WXResources>();

	/**
	 * return null if not available on the disk
	 */
	public static WXResources getResource(SiteSpec site, FileSpec file) {
		WXResources rl = loaders.get(file.id());
		if (rl != null)
			return rl;

		String[] deps = file.deps();
		WXResources[] rs = null;
		if (deps != null) {
			rs = new WXResources[deps.length];
			for (int i = 0; i < deps.length; i++) {
				FileSpec pf = site.getFile(deps[i]);
				if (pf == null)
					return null;
				WXResources r = getResource(site, pf);
				if (r == null)
					return null;
				rs[i] = r;
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

		try {
			AssetManager am = (AssetManager) AssetManager.class.newInstance();
			am.getClass().getMethod("addAssetPath", String.class)
					.invoke(am, path.getAbsolutePath());

			// parse packageName from AndroidManifest.xml
			String packageName = null;
			XmlResourceParser xml = am
					.openXmlResourceParser("AndroidManifest.xml");
			int eventType = xml.getEventType();
			xmlloop: while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_TAG:
					if ("manifest".equals(xml.getName())) {
						packageName = xml.getAttributeValue(
								"http://schemas.android.com/apk/res/android",
								"package");
						break xmlloop;
					}
				}
				eventType = xml.nextToken();
			}
			xml.close();
			if (packageName == null) {
				throw new RuntimeException(
						"package not found in AndroidManifest.xml [" + path
								+ "]");
			}

			Resources superRes = WarrantixApplication.getInstance().getResources();
			Resources res = new Resources(am, superRes.getDisplayMetrics(),
					superRes.getConfiguration());

			rl = new WXResources(file, packageName, res, am, rs);
		} catch (Exception e) {
			if (e instanceof RuntimeException)
				throw (RuntimeException) e;
			throw new RuntimeException(e);
		}

		loaders.put(file.id(), rl);
		return rl;
	}

	/**
	 * 从当前类所在的包载入MyResource
	 * 
	 * @param clazz
	 * @return
	 * @throws RuntimeException
	 *             如果当前类不是动态加载包载入的
	 */
	public static WXResources getResource(Class<?> clazz) {
		if (!(clazz.getClassLoader() instanceof WXClassLoader)) {
			throw new RuntimeException(clazz
					+ " is not loaded from dynamic loader");
		}
		return getResource((WXClassLoader) clazz.getClassLoader());
	}

	static WXResources getResource(WXClassLoader mcl) {
		FileSpec file = mcl.file;
		WXResources rl = loaders.get(file.id());
		if (rl != null)
			return rl;

		WXResources[] rs = null;
		if (mcl.deps != null) {
			rs = new WXResources[mcl.deps.length];
			for (int i = 0; i < rs.length; i++) {
				WXResources r = getResource(mcl.deps[i]);
				rs[i] = r;
			}
		}

		File dir = WarrantixApplication.getInstance().getFilesDir();
		dir = new File(dir, "repo");
		if (!dir.isDirectory())
			throw new RuntimeException(dir + " not exists");
		dir = new File(dir, file.id());
		File path = new File(dir, TextUtils.isEmpty(file.md5()) ? "1.apk"
				: file.md5() + ".apk");
		if (!path.isFile())
			throw new RuntimeException(path + " not exists");

		try {
			AssetManager am = (AssetManager) AssetManager.class.newInstance();
			am.getClass().getMethod("addAssetPath", String.class)
					.invoke(am, path.getAbsolutePath());

			Resources superRes = WarrantixApplication.getInstance().getResources();
			Resources res = new Resources(am, superRes.getDisplayMetrics(),
					superRes.getConfiguration());

			// parse packageName from AndroidManifest.xml
			String packageName = null;
			XmlResourceParser xml = am
					.openXmlResourceParser("AndroidManifest.xml");
			int eventType = xml.getEventType();
			xmlloop: while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_TAG:
					if ("manifest".equals(xml.getName())) {
						packageName = xml.getAttributeValue(null, "package");
						break xmlloop;
					}
				}
				eventType = xml.nextToken();
			}
			xml.close();
			if (packageName == null) {
				throw new RuntimeException(
						"package not found in AndroidManifest.xml [" + path
								+ "]");
			}

			rl = new WXResources(file, packageName, res, am, rs);
		} catch (Exception e) {
			if (e instanceof RuntimeException)
				throw (RuntimeException) e;
			throw new RuntimeException(e);
		}

		loaders.put(file.id(), rl);
		return rl;
	}
}
