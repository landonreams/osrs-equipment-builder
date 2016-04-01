package osrs.util;

import java.util.EnumMap;
import java.util.Set;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import osrs.model.data.ArmorStats;

public class ArmorStatsEnumMapAdapter extends XmlAdapter<Integer[], EnumMap<ArmorStats, Integer>> {

	@Override
	public EnumMap<ArmorStats, Integer> unmarshal(Integer[] v) throws Exception {
		EnumMap<ArmorStats, Integer> map = new EnumMap<ArmorStats, Integer>(ArmorStats.class);
		for(int i = 0; i < v.length; i++) {
			map.put(ArmorStats.fromIndex(i), v[i]);
		}
		return map;
	}

	@Override
	public Integer[] marshal(EnumMap<ArmorStats, Integer> v) throws Exception {
		Set<ArmorStats> values = v.keySet();
		Integer[] result = new Integer[values.size()];

		for(ArmorStats key : values) {
			result[key.index()] = v.get(key);
		}

		return result;
	}
}
