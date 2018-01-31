package com.gox.manage.service;

import java.util.List;

import com.gox.manage.model.Juan;

public interface JuanDao {

	Juan findJuan(Long id);

	void save(Juan j);

	Juan update(Long id);

	List<Juan> findTodayTrank();

	List<Juan> findByUserId(Long uId);

	Juan findJuanByNew(Long userId);

	List<Juan> findHjById(Long userId);

	List<Juan> findAllByuserId(Long userId);

	void updateBybx(Juan j);

	Juan findLqTime(Long userId);

	Juan findHJnew(Long userId);

	List<Juan> findShareLB(Long userId);

	Juan findZslb(Long userId);

	List<Juan> findZZLB(Long userId);

	List<Juan> findZZLBX(Long userId);

	List<Juan> findAllAward(Long userId);

}
