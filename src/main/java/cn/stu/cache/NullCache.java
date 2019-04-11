package cn.stu.cache;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 空的缓存Provider
 * @author zhanghanlin
 */
public class NullCache implements Level1Cache, Level2Cache {

	@Override
	public long ttl() {
		return -1;
	}

	@Override
	public long size() {
		return 0;
	}

	@Override
	public Object get(String key) {
		return null;
	}

	@Override
	public void put(String key, Object value) {
	}

	@Override
	public Collection<String> keys() {
		return Collections.emptyList();
	}

	@Override
	public Map get(Collection<String> keys) {
		return Collections.emptyMap();
	}

	@Override
	public boolean exists(String key) {
		return false;
	}

	@Override
	public void put(Map<String, Object> elements)  {
	}

	@Override
	public byte[] getBytes(String key) {
		return null;
	}

	@Override
	public List<byte[]> getBytes(Collection<String> key) {
		return Collections.emptyList();
	}

	@Override
	public void setBytes(String key, byte[] bytes) {
	}

	@Override
	public void setBytes(Map<String,byte[]> bytes) {
	}

	@Override
	public void evict(String...keys) {
	}

	@Override
	public void clear() {

	}
}
