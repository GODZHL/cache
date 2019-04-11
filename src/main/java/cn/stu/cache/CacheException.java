package cn.stu.cache;

/**
 * ZhCache exception
 *
 * @author zhanghanlin
 */
public class CacheException extends RuntimeException {

	public CacheException(String s) {
		super(s);
	}

	public CacheException(String s, Throwable e) {
		super(s, e);
	}

	public CacheException(Throwable e) {
		super(e);
	}
	
}
