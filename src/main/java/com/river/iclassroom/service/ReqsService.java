package com.river.iclassroom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.iclassroom.dao.BorrowDao;
import com.river.iclassroom.dao.ReservationDao;
import com.river.iclassroom.model.AdminRecord;
import com.river.iclassroom.model.Reservation;

@Service
public class ReqsService {
	@Autowired
	private ReservationDao reservationDao;
	@Autowired
	private BorrowDao borrowDao;

	/**
	*	old 获取已处理的预约请求
	*	ack 获取同意的预约请求
	*	nck 获取拒绝的预约请求
	*	new 获取未处理的预约请求
	*/
	public List<AdminRecord> getReqs(String type) {
		List<AdminRecord> res;
		if("old".equals(type)) {
			res = reservationDao.getAdminRecordsByResult(0, true);
		} else {
			int result = 0;
			if("ack".equals(type)) {
				result = 1;
			} else if ("nck".equals(type)) {
				result = 2;
			}
			res = reservationDao.getAdminRecordsByResult(result);
		}
		if(res != null) {
			for (AdminRecord item : res) {
				item.setBorrows(borrowDao.queryBorrows(item.getId()));
			}
		}
		return res;
	}

	/**
	 * return 0		result参数错误
	 * return 1		处理成功
	 * return 2		处理失败，预约不存在或已被取消
	 * return 3		处理失败，预约内容与已同意的预约冲突
	 * return 4		错误，预约已处理
	 *
	 * result==1	同意
	 * result==2	拒绝
	 */
	public int handleReqs(String id, int result, String remarks) {
		if(result != 1 && result != 2) {
			return 0;
		}

		Reservation temp = reservationDao.getReservation(id);
		if(temp == null) {
			return 2;
		}

		if(temp.getResult() != 0) {
			return 4;
		}

		if(result == 1 && reservationDao.isConflicted(temp.getClassroom(), temp.getReserveDate(), temp.getStartCourse(), temp.getEndCourse())) {
			return 3;
		}

		reservationDao.updateResult(id, result, remarks);
		return 1;
	}
}