/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.service;

import java.util.List;
import java.util.Map;

import com.baiwang.einvoice.qz.beans.ReportDetail;

/**
  * @ClassName: ReportDetailService
  * @Description: TODO
  * @author Administrator
  * @date 2016年3月3日 下午8:21:46
  */
public interface ReportDetailService {
	ReportDetail getFpByLSH(String fpqqlsh);
	
    List<ReportDetail> getFpListByCondition(Map<String, Object> condition);
}
