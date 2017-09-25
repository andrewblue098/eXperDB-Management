package com.k4m.dx.tcontrol.admin.accesshistory.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.k4m.dx.tcontrol.common.service.HistoryVO;
import com.k4m.dx.tcontrol.login.service.UserVO;
import com.k4m.dx.tcontrol.sample.service.PagingVO;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("accessHistoryDAO")
public class AccessHistoryDAO extends EgovAbstractMapper {
	
	/**
	 * 화면접근 이력 등록
	 * 
	 * @param historyVO
	 * @throws SQLException
	 */
	public void insertHistory(HistoryVO historyVO) throws SQLException {
		insert("accessHistorySql.insertHistory", historyVO);
	}

	/**
	 * 화면명 조회
	 * 
	 * @return HistoryVO
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<HistoryVO> selectAccessScreenName() {
		List<HistoryVO> result = null;
		result = (List<HistoryVO>) list("accessHistorySql.selectAccessScreenName", "");
		return result;
	}

	/**
	 * 화면접근 내역 조회
	 * 
	 * @param userVO
	 * @return List
	 * @throws SQLException
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<UserVO> selectAccessHistory(PagingVO searchVO, Map<String, Object> param) {
		List<UserVO> result = null;
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("lgi_dtm_start", param.get("lgi_dtm_start"));
		params.put("lgi_dtm_end", param.get("lgi_dtm_end"));
		params.put("type", param.get("type"));
		params.put("search", param.get("search"));
		params.put("order_type", param.get("order_type"));
		params.put("order", param.get("order"));
		params.put("sys_cd", param.get("sys_cd"));
		params.put("recordCountPerPage", searchVO.getRecordCountPerPage());
		params.put("firstIndex", searchVO.getFirstIndex());

		result = (List<UserVO>) list("accessHistorySql.selectAccessHistory", params);
		return result;
	}

	/**
	 * 화면접근 내역 총 갯수
	 * 
	 * @param param
	 * 
	 * @return TotCnt
	 * @throws SQLExceptio
	 */
	public int selectAccessHistoryTotCnt(Map<String, Object> param) throws SQLException {
		int TotCnt = 0;
		TotCnt = (int) getSqlSession().selectOne("accessHistorySql.selectAccessHistoryTotCnt", param);
		return TotCnt;
	}

	/**
	 * 엑셀 화면접근 이력 조회
	 * 
	 * @param param
	 * @throws SQLException
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<UserVO> selectAccessHistoryExcel(Map<String, Object> param) throws SQLException {
		List<UserVO> result = null;
		result = (List<UserVO>) list("accessHistorySql.selectAccessHistoryExcel", param);
		return result;
	}

}
