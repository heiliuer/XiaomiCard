package com.heiliuer.xiaomi_card;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Environment;
import android.text.ClipboardManager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/**
 * @author hao.wang
 */

@SuppressLint("SimpleDateFormat")
@SuppressWarnings("deprecation")
public class Utils {

	/**
	 * 隐藏状态栏<br>
	 * <b>在setContentView之前调用</b>
	 * 
	 * @param act
	 */
	public static void hideStatusBar(Activity act) {
		Window wd = act.getWindow();
		WindowManager.LayoutParams attrs = wd.getAttributes();
		attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
		wd.setAttributes(attrs);
	}

	/**
	 * 显示状态栏 <b>在setContentView之前调用</b>
	 * 
	 * @param act
	 */
	public static void showStatusBar(Activity act) {
		Window wd = act.getWindow();
		WindowManager.LayoutParams attrs = wd.getAttributes();
		attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
		wd.setAttributes(attrs);
	}

	/**
	 * 视图嵌入，状态栏位于顶层(frame)
	 * 
	 * @param act
	 */
	public static void embedContentView(Activity act) {
		Window wd = act.getWindow();
		wd.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
		wd.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
	}

	/**
	 * 透明状态栏
	 * 
	 * @param act
	 */
	@TargetApi(19)
	public static void transparentStatusBar(Activity act) {
		if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
			Window wd = act.getWindow();
			// 透明状态栏
			wd.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			// 透明导航栏
			wd.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}
	}

	/**
	 * 调用小米沉浸式状态栏
	 * 
	 * @param act
	 */
	public static void immerseStatusBar(Activity act, boolean isBlack) {
		Window window = act.getWindow();
		Class<? extends Window> clazz = window.getClass();
		try {
			int tranceFlag = 0;
			int darkModeFlag = 0;
			Class<?> layoutParams = Class
					.forName("android.view.MiuiWindowManager$LayoutParams");

			Field field = layoutParams
					.getField("EXTRA_FLAG_STATUS_BAR_TRANSPARENT");
			tranceFlag = field.getInt(layoutParams);

			field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
			darkModeFlag = field.getInt(layoutParams);

			Method extraFlagField = clazz.getMethod("setExtraFlags", int.class,
					int.class);
			// 只需要状态栏透明
			extraFlagField.invoke(window, tranceFlag, tranceFlag);
			if (isBlack) {
				// 状态栏透明且黑色字体
				extraFlagField.invoke(window, tranceFlag | darkModeFlag,
						tranceFlag | darkModeFlag);
			}
			// 清除黑色字体
			// extraFlagField.invoke(window, 0, darkModeFlag);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加载动画
	 */
	public static Animation getAnim(Context con, int res) {
		Animation animation;
		animation = AnimationUtils.loadAnimation(con, res);
		return animation;
	}

	/**
	 * 检查网络是否断线
	 * 
	 * @param c
	 * @return
	 */
	// begin
	public static boolean isNetNotAccess(Context c) {
		return ((ConnectivityManager) c.getSystemService("connectivity"))
				.getActiveNetworkInfo() == null;
	}

	/**
	 * 得到curent-base毫米数间的时间间隔，结果x小时x分钟
	 * 
	 * @param base
	 * @param current
	 * @return
	 */
	public static String getTimeGap(long base, long current) {
		if (current < base)
			throw new IllegalArgumentException("current < base!");
		long h = (current - base) / 60000;
		current = h / 60;
		StringBuilder t = new StringBuilder(9);
		if (current != 0) {
			t.append(current).append("小时");
		}
		current = h % 60;
		return t.append(current).append("分钟").toString();
	}

	// end

	/**
	 * 解析输入流成字符串
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	// begin
	public static String getStringFromInputStream(InputStream in)
			throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte bs[] = new byte[1024];
		int pos = 0;
		while ((pos = in.read(bs)) != -1) {
			outputStream.write(bs, 0, pos);
		}
		outputStream.close();
		in.close();
		return new String(outputStream.toByteArray());
	}

	// end

	// begin
	private static Context context;
	private static Toast toast;

	/**
	 * 吐司,禁止在非UI线程中调用
	 * 
	 * @param c
	 * @param resId
	 */
	public static void showToast(Context c, int resId) {
		showToast(c, c.getString(resId));
	}

	public static void showNewToast(Context c, int resId) {
		showNewToast(c, c.getString(resId));
	}

	public static void showNewToast(Context c, String s) {
		toast = null;
		showToast(c, s);
	}

	public static void showToast(Context c, String txt) {
		if (toast == null || c != context) {
			context = c;
			toast = Toast.makeText(c, "", Toast.LENGTH_SHORT);
		}
		toast.setText(txt);
		toast.show();
	}

	public static Runnable getToastRunnable(final String title, final Context c) {
		return new Runnable() {
			@Override
			public void run() {
				showToast(c, title);

			}
		};
	}

	public static Runnable getToastRunnable(final int strId, final Context c) {
		return new Runnable() {
			@Override
			public void run() {
				showToast(c, strId);
			}
		};
	}

	// end

	/**
	 * 计算文件大小（单位MB）
	 * 
	 * @param byteN
	 * @return
	 */
	// begin
	public static String tranBytetoMB(int byteN) {
		if (byteN < 1024)
			return byteN + "B";
		else if (byteN < 1048579) {// 1024*1024
			byteN = (int) (byteN / 102.4);
			return byteN / 10 + "." + byteN % 10 + "KB";
		} else {// 1024*1024*1024
			byteN = (int) (byteN / 104857.9);
			return byteN / 10 + "." + byteN % 10 + "MB";
		}
	}

	private static final String CHINESE_STRING = "[\u4e00-\u9fa5]{1,}|\\b[A-Za-z]+\\b";

	/**
	 * 给单词和中文词语加上超链接
	 * 
	 * @param s
	 * @return
	 */
	// begin
	public static String AddAchor(String s) {
		StringBuffer buffer = new StringBuffer();
		Pattern p = Pattern.compile(CHINESE_STRING);
		Matcher m = p.matcher(s);
		int start = 0;
		while (m.find()) {
			buffer.append(s.substring(start, m.start()));
			start = m.end();
			buffer.append("<a href='" + m.group() + "' />" + m.group() + "</a>");
		}
		Utils.l(buffer.toString());
		return buffer.toString();
	}

	public boolean setTextLink(TextView txt) {
		txt.setMovementMethod(LinkMovementMethod.getInstance());
		CharSequence text = txt.getText();
		if (text instanceof Spannable) {
			Spannable sp = (Spannable) txt.getText();
			URLSpan[] urls = sp.getSpans(0, text.length(), URLSpan.class);
			SpannableStringBuilder style = new SpannableStringBuilder(text);
			style.clearSpans();
			for (URLSpan url : urls) {
				MyURLSpan myURLSpan = new MyURLSpan(url.getURL());
				style.setSpan(myURLSpan, sp.getSpanStart(url),
						sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
			txt.setText(style);
		}
		return true;
	}

	private static class MyURLSpan extends ClickableSpan {
		@SuppressWarnings("unused")
		private String mUrl;

		MyURLSpan(String url) {
			mUrl = url;
		}

		@Override
		public void updateDrawState(TextPaint ds) {
			ds.setColor(ds.linkColor);
			ds.setUnderlineText(false);
		}

		@Override
		public void onClick(View widget) {
			// 点击链接操作 url
			mUrl = "";
		}
	}

	// end
	/**
	 * 得到剪切板的内容
	 * 
	 * @param c
	 * @return
	 */
	public static String getClipBoardText(Context c) {
		if (c == null) {
			return null;
		}

		String str = ((ClipboardManager) c
				.getSystemService(Context.CLIPBOARD_SERVICE)).getText()
				.toString();
		return str;
	}

	/**
	 * 复制内容到剪切板
	 * 
	 * @param c
	 * @param str
	 */
	public static void putTextTo(Context c, String str) {
		((ClipboardManager) c.getSystemService(Context.CLIPBOARD_SERVICE))
				.setText(str);
	}

	/**
	 * 隐藏输入法
	 * 
	 * @param c
	 */
	public static void hideInputMethod(Activity c) {
		if (c == null) {
			return;
		}
		if (inputMethodManager == null) {
			inputMethodManager = (InputMethodManager) c
					.getSystemService(Context.INPUT_METHOD_SERVICE);
		}
		if (inputMethodManager.isActive()) {
			inputMethodManager.hideSoftInputFromWindow(((Activity) c)
					.getCurrentFocus().getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	private static InputMethodManager inputMethodManager;

	/**
	 * 转换毫秒事件为00:00格式
	 */
	// begin
	public static String getTimeString(int t) {
		t /= 1000;
		String s = "";
		int tmp1 = t / 60, tmp2 = t % 60;
		if (tmp1 < 10) {
			s += "0" + tmp1 + ":";
		} else {
			s += tmp1 + ":";
		}
		if (tmp2 < 10) {
			s += "0" + tmp2;
		} else {
			s += tmp2;
		}
		return s;
	}

	public static boolean LOG_OFF;

	/**
	 * 打日志 tag=myLog
	 * 
	 * @param s
	 * @Deprecated use {@link #i(Object)} or {@link #d(Object)} for instead
	 */
	public static void l(Object s) {
		if (LOG_OFF)
			return;
		if (s == null) {
			s = "";
		}
		Log.v("myLog", s.toString());
	}

	public static boolean LOG_INFO_OFF;

	/**
	 * 打日志 tag=INFO
	 * 
	 * @param s
	 *            print "null" if null
	 */
	public static void i(Object s) {
		i("INFO", s);
	}

	/**
	 * 打日志 tag=INFO
	 * 
	 * @param s
	 *            print "null" if null
	 */
	public static void i(String tag, Object s) {
		if (LOG_INFO_OFF)
			return;
		if (s == null) {
			s = "null";
		}
		Log.i(tag, s.toString());
	}

	public static boolean LOG_DEBUG_OFF;

	/**
	 * 打日志 tag=DEBUG
	 * 
	 * @param s
	 *            print "null" if null
	 */
	public static void d(Object s) {
		d("DEBUG", s);
	}

	/**
	 * 打日志 tag=DEBUG
	 * 
	 * @param s
	 *            print "null" if null
	 */
	public static void d(String tag, Object s) {
		if (LOG_DEBUG_OFF)
			return;
		if (s == null) {
			s = "null";
		}
		Log.d(tag, s.toString());
	}

	public static boolean copy(String from, String to) {
		File file = new File(from);
		if (!file.exists())
			throw new RuntimeException("from 文件不存在");
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
			inputStream.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 解压缩含有文件夹的压缩文件
	 * 
	 * @param zipFile
	 * @param folderPath
	 * @throws ZipException
	 * @throws IOException
	 */
	public void upZipWithFile(File zipFile, String folderPath)
			throws ZipException, IOException {
		File desDir = new File(folderPath);
		if (!desDir.exists()) {
			// 创建目标目录
			desDir.mkdirs();
		}
		ZipFile zf = new ZipFile(zipFile);
		for (Enumeration<?> entries = zf.entries(); entries.hasMoreElements();) {
			ZipEntry entry = ((ZipEntry) entries.nextElement());
			if (entry.isDirectory()) {
				String tmpStr = folderPath + File.separator + entry.getName();
				tmpStr = new String(tmpStr.getBytes("8859_1"), "GB2312");
				File folder = new File(tmpStr);
				folder.mkdirs();
			} else {
				InputStream is = zf.getInputStream(entry);
				String str = folderPath + File.separator + entry.getName();
				// 转换编码，避免中文时乱码
				str = new String(str.getBytes("8859_1"), "GB2312");
				File desFile = new File(str);
				if (!desFile.exists()) {
					// 创建目标文件
					desFile.createNewFile();
				}
				OutputStream os = new FileOutputStream(desFile);
				byte[] buffer = new byte[1024];
				int realLength;
				while ((realLength = is.read(buffer)) > 0) {
					os.write(buffer, 0, realLength);
					os.flush();
				}
				is.close();
				os.close();
			}
		}
		zf.close();
	}

	/**
	 * 解压缩不含文件夹的压缩包
	 * 
	 * @param zipFile
	 * @param folderPath
	 * @throws ZipException
	 * @throws IOException
	 */
	public void upZipFile(File zipFile, String folderPath) throws ZipException,
			IOException {
		File desDir = new File(folderPath);
		if (!desDir.exists()) {
			// 创建目标目录
			desDir.mkdirs();
		}

		ZipFile zf = new ZipFile(zipFile);
		for (Enumeration<?> entries = zf.entries(); entries.hasMoreElements();) {
			ZipEntry entry = ((ZipEntry) entries.nextElement());
			InputStream is = zf.getInputStream(entry);
			String str = folderPath + File.separator + entry.getName();
			// 转换编码，避免中文时乱码
			str = new String(str.getBytes("8859_1"), "GB2312");
			File desFile = new File(str);
			if (!desFile.exists()) {
				File fileParentDir = desFile.getParentFile();
				if (!fileParentDir.exists()) {
					// 创建目标文件的父目录
					fileParentDir.mkdirs();
				}
				// 创建目标文件
				desFile.createNewFile();
			}
			OutputStream os = new FileOutputStream(desFile);
			byte[] buffer = new byte[1024];
			int realLength;
			while ((realLength = is.read(buffer)) > 0) {
				os.write(buffer, 0, realLength);
				os.flush();
			}
			is.close();
			os.close();
		}
		zf.close();
	}

	public static boolean upgradeRootPermission(String pkgCodePath) {
		Process process = null;
		DataOutputStream os = null;
		try {
			String cmd = "chmod 777 " + pkgCodePath;
			process = Runtime.getRuntime().exec("su"); // 切换到root帐号
			os = new DataOutputStream(process.getOutputStream());
			os.writeBytes(cmd + "\n");
			os.writeBytes("exit\n");
			os.flush();
			process.waitFor();
		} catch (Exception e) {
			return false;
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				process.destroy();
			} catch (Exception e) {
			}
		}
		return true;
	}

	@SuppressLint("SimpleDateFormat")
	private static final SimpleDateFormat formatter = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * yyyy-MM-dd HH:mm:ss
	 * 
	 * @param time
	 * @return
	 */
	public static String formatTimeYMD_HMS(long time) {
		return formatter.format(time);
	}

	private static final SimpleDateFormat formatterHms = new SimpleDateFormat(
			"HH:mm:ss");

	/**
	 * HH:mm:ss
	 * 
	 * @param time
	 * @return
	 */
	public static String formatTimeHMS(long time) {
		return formatterHms.format(time);
	}

	/**
	 * 返回整数高位补零
	 * 
	 * @param x奇数为数字
	 *            ，偶数为需要格式化的为数 如 1,3,234,2结果是00134
	 * @return
	 */
	public static String getAutoInsertZeroNum(int... x) {
		if (x.length <= 1 || x.length % 2 != 0) {
			throw new RuntimeException("参数数目不对劲");
		}
		StringBuffer sb = new StringBuffer();
		String str;
		for (int i = 0; i < x.length; i += 2) {
			str = "" + x[i];
			if (str.length() > x[i + 1]) {
				str = str.substring(str.length() - x[i + 1], str.length());
			} else if (str.length() < x[i + 1]) {
				for (int j = 0; j < x[i + 1] - str.length(); j++) {
					sb.append('0');
				}
			}
			sb.append(str);
		}
		return sb.toString();
	}

	/**
	 * 
	 * @return false if SdCard is accessible ,true if else;
	 */
	public static boolean checkStorageNotAccess() {
		return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED;
	}

	/**
	 * 
	 * @return true if SdCard is accessible ,false if else;
	 */
	public boolean checkStorageAccess() {
		return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED;
	}
}
