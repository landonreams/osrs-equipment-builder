package osrs.util;

import java.util.EnumMap;
import java.util.Set;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import osrs.model.data.Levels;

public class LevelsEnumMapAdapter extends XmlAdapter<Integer[], EnumMap<Levels, Integer>> {

	@Override
	public EnumMap<Levels, Integer> unmarshal(Integer[] v) throws Exception {
		EnumMap<Levels, Integer> map = new EnumMap<Levels, Integer>(Levels.class);
		for(int i = 0; i < v.length; i++) {
			map.put(Levels.fromIndex(i), v[i]);
		}
		return map;
	}

	@Override
	public Integer[] marshal(EnumMap<Levels, Integer> v) throws Exception {
		Set<Levels> values = v.keySet();
		Integer[] result = new Integer[values.size()];

		for(Levels key : values) {
			result[key.index()] = v.get(key);
		}

		return result;
	}
}
