/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.service.impl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.baiwang.einvoice.qz.beans.Fpmx;
import com.baiwang.einvoice.qz.beans.Kpxx;
import com.baiwang.einvoice.qz.beans.OrderDetail;
import com.baiwang.einvoice.qz.dao.FpmxMapper;
import com.baiwang.einvoice.qz.dao.KpxxMapper;
import com.baiwang.einvoice.qz.dao.OrderDetailMapper;
import com.baiwang.einvoice.qz.service.IFpService;
import com.baiwang.einvoice.qz.service.PageServiceImpl;
import com.baiwang.einvoice.qz.utils.XmlUtil;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
  * @ClassName: FpServiceImpl
  * @Description: TODO
  * @author wsdoing
  * @date 2016年3月3日 上午9:25:47
  */
@Service("fpService")
public class FpServiceImpl implements IFpService {

	private static final Log logger = LogFactory.getLog(FpServiceImpl.class);
	
	@Resource
	private KpxxMapper dao;
	@Resource
	private FpmxMapper fpmxDao;
	@Resource
	private OrderDetailMapper orderDetailDao;
	@Resource
	private PageServiceImpl pageService;
	

	/**
	  * <p>Title: saveInfo</p>
	  * <p>Description: </p>
	  * @param kpxx
	  * @param fpmxList
	  * @see com.baiwang.einvoice.qz.service.IFpService#saveInfo(com.baiwang.einvoice.qz.beans.Kpxx, java.util.List)
	  */
	@Override
	public void saveInfo(OrderDetail orderDetail, Kpxx kpxx, List<Fpmx> fpmxList , String fpqqlsh) {
		
		String zddh = orderDetail.getZddh();
		String fddh = orderDetail.getFddh();
		
		Kpxx tempKpxx = dao.selectByDdhm(zddh, fddh);
		if(tempKpxx != null){
			String tempFpqqlsh = tempKpxx.getFpqqlsh();
			orderDetailDao.deleteByFpqqlsh(tempFpqqlsh);
			logger.info("订单信息重复，删除订单号为【"+zddh+"/"+fddh+"】的开票信息！");
			dao.deleteByPrimaryKey(tempFpqqlsh);
			logger.info("发票信息重复，删除发票请求流水号为【"+tempFpqqlsh+"】的开票信息！");
			fpmxDao.deleteByFpqqlsh(tempFpqqlsh);
			logger.info("删除发票请求流水号为【"+tempFpqqlsh+"】的发票明细！");
		}
		orderDetail.setFpqqlsh(fpqqlsh);
		orderDetailDao.insert(orderDetail);
		logger.info("存入订单号为【"+zddh+"/"+fddh+"】的订单信息！");
		
		kpxx.setZddh(zddh);
		kpxx.setFddh(fddh);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		kpxx.setKprq(sf.format(new Date()));
		kpxx.setFpzt("1");
		kpxx.setFpqqlsh(fpqqlsh);
		dao.insert(kpxx);
		logger.info("存入订单号为【"+zddh+"/"+fddh+"】的开票信息！");
		
		
		if(fpmxList.size()>0){
			for(Fpmx fpmx: fpmxList){
				fpmx.setFpqqlsh(fpqqlsh);
			}
			
			fpmxDao.insertFromList(fpmxList);
			logger.info("存入流水号为【"+kpxx.getFpqqlsh()+"】的发票明细！");
		}
		
	}

	/**
	  * <p>Title: getPlainList</p>
	  * <p>Description: </p>
	  * @return
	  * @see com.baiwang.einvoice.qz.service.IFpService#getPlainList()
	  */
	@Override
	public List<Map<String, String>> getPlainList(HashMap<String, String> param) {
		
		// TODO Auto-generated method stub
		return orderDetailDao.getPlainList(param);
		
	}

	/**
	  * <p>Title: getKpxxByFpqqlsh</p>
	  * <p>Description: </p>
	  * @param fpqqlsh
	  * @return
	  * @see com.baiwang.einvoice.qz.service.IFpService#getKpxxByFpqqlsh(java.lang.String)
	  */
	@Override
	public Kpxx getKpxxByFpqqlsh(String fpqqlsh) {
		
		// TODO Auto-generated method stub
		return dao.selectByFpqqlsh(fpqqlsh);
		
	}

	/**
	  * <p>Title: getFpmxByFpqqlsh</p>
	  * <p>Description: </p>
	  * @param fpqqlsh
	  * @return
	  * @see com.baiwang.einvoice.qz.service.IFpService#getFpmxByFpqqlsh(java.lang.String)
	  */
	@Override
	public List<Fpmx> getFpmxByFpqqlsh(String fpqqlsh) {
		
		// TODO Auto-generated method stub
		return fpmxDao.selectByFpqqlsh(fpqqlsh);
		
	}

	/**
	  * <p>Title: updateFpztByFpqqlsh</p>
	  * <p>Description: </p>
	  * @param fpqqlsh
	  * @see com.baiwang.einvoice.qz.service.IFpService#updateFpztByFpqqlsh(java.lang.String)
	  */
	@Override
	public void updateFpztByFpqqlsh(String fpqqlsh) {
		
		// TODO Auto-generated method stub
		dao.updateFpztByFpqqlsh(fpqqlsh);
	}

	/**
	  * <p>Title: getSpecialList</p>
	  * <p>Description: </p>
	  * @param param
	  * @return
	  * @see com.baiwang.einvoice.qz.service.IFpService#getSpecialList(java.util.HashMap)
	  */
	@Override
	public List<Map<String, String>> getSpecialList(HashMap<String, String> param) {
		
		// TODO Auto-generated method stub
		return orderDetailDao.getSpecialList(param);
		
	}

	/**
	  * <p>Title: getXml</p>
	  * <p>Description: </p>
	  * @param kpxx
	  * @param fpmxList
	  * @return
	  * @see com.baiwang.einvoice.qz.service.IFpService#getXml(com.baiwang.einvoice.qz.beans.Kpxx, java.util.List)
	  */
	@Override
	public String getXml(Kpxx kpxx, List<Fpmx> fpmxList) {
		
		String xml = "";
		
		if(kpxx != null){
			try {
			if("004".equals(kpxx.getFplx())){
				xml = XmlUtil.toSpecialInvoice(kpxx, fpmxList);
				logger.info("开具增值税纸质专用发票，报文为："+ xml);
			}else if("007".equals(kpxx.getFplx())){
				xml = XmlUtil.toPlainInvoice(kpxx, fpmxList);
				logger.info("开具增值税纸质普通发票，报文为："+ xml);
			}else if("026".equals(kpxx.getFplx())){
				xml = XmlUtil.toEInvoice(kpxx, fpmxList);
				logger.info("开具增值税电子普通发票，报文为："+ xml);
			}
			} catch (UnsupportedEncodingException e) {
				
				e.printStackTrace();
				logger.error("报文封装失败：" + e.getMessage());
			}
		}
		
		return xml;
	}

	/**
	  * <p>Title: list</p>
	  * <p>Description: </p>
	  * @param param
	  * @param pageIndex
	  * @param pageSize
	  * @return
	  * @see com.baiwang.einvoice.qz.service.IFpService#list(java.util.HashMap, int, int)
	  */
	@SuppressWarnings("unchecked")
	@Override
	public List<HashMap<String, Object>> listPlain(HashMap<String, String> param, int pageIndex, int pageSize) {
		
		// TODO Auto-generated method stub
		return (PageList<HashMap<String, Object>>) pageService.getPageList(OrderDetailMapper.class, "getPlainList",param, pageIndex, pageSize);
		
	}

	/**
	  * <p>Title: listSpecial</p>
	  * <p>Description: </p>
	  * @param param
	  * @param pageIndex
	  * @param pageSize
	  * @return
	  * @see com.baiwang.einvoice.qz.service.IFpService#listSpecial(java.util.HashMap, int, int)
	  */
	@SuppressWarnings("unchecked")
	@Override
	public List<HashMap<String, Object>> listSpecial(HashMap<String, String> param, int pageIndex, int pageSize) {
		
		// TODO Auto-generated method stub
		return (PageList<HashMap<String, Object>>) pageService.getPageList(OrderDetailMapper.class, "getSpecialList",param, pageIndex, pageSize);

	}

	/**
	  * <p>Title: updateCallBackInfo</p>
	  * <p>Description: </p>
	  * @param kpxx
	  * @see com.baiwang.einvoice.qz.service.IFpService#updateCallBackInfo(com.baiwang.einvoice.qz.beans.Kpxx)
	  */
	@Override
	public void saveCallBackInfo(Kpxx kpxx) {
		
		// TODO Auto-generated method stub
		dao.saveCallBackInfo(kpxx);
	}

	/**
	  * @author Administrator
	  * @Description: TODO
	  * @param @param param
	  * @param @return  
	  * @throws
	  * @date 2016年3月11日 上午11:51:48
	  */
	@Override
	public List<Map<String, Object>> getPlainList4zf(Map<String, Object> param) {
		return orderDetailDao.getPlainList4zf(param);
	}

	/**
	  * @author Administrator
	  * @Description: TODO
	  * @param @param param
	  * @param @return  
	  * @throws
	  * @date 2016年3月11日 上午11:51:48
	  */
	@Override
	public List<Map<String, Object>> getSpecialList4zf(Map<String, Object> param) {
		return orderDetailDao.getSpecialList4zf(param);
	}

	/**
	  * @author Administrator
	  * @Description: TODO
	  * @param @param fpqqlsh
	  * @param @return  
	  * @throws
	  * @date 2016年3月14日 上午10:32:53
	  */
	@Override
	public int zfByFpqqlsh(String fpqqlsh) {
		return dao.zfByFpqqlsh(fpqqlsh);
	}

	/**
	  * @author Administrator
	  * @Description: TODO
	  * @param @param param
	  * @param @return  
	  * @throws
	  * @date 2016年3月16日 下午1:16:04
	  */
	@Override
	public int getPlainList4zfCount(Map<String, Object> param) {
		
		return orderDetailDao.getPlainList4zfCount(param);
		
	}

	/**
	  * @author Administrator
	  * @Description: TODO
	  * @param @param param
	  * @param @return  
	  * @throws
	  * @date 2016年3月16日 下午1:16:04
	  */
	@Override
	public int getSpecialList4zfCount(Map<String, Object> param) {
		
		return orderDetailDao.getSpecialList4zfCount(param);
		
	}

	/**
	  * @author Administrator
	  * @Description: TODO
	  * @param @param param
	  * @param @return  
	  * @throws
	  * @date 2016年3月17日 上午11:37:47
	  */
	@Override
	public List<Map<String, Object>> getPlainList4ch(Map<String, Object> param) {
		
		// TODO Auto-generated method stub
		return orderDetailDao.getPlainList4ch(param);
		
	}

	/**
	  * @author Administrator
	  * @Description: TODO
	  * @param @param param
	  * @param @return  
	  * @throws
	  * @date 2016年3月17日 上午11:37:47
	  */
	@Override
	public List<Map<String, Object>> getSpecialList4ch(Map<String, Object> param) {
		
		// TODO Auto-generated method stub
		return orderDetailDao.getSpecialList4ch(param);
		
	}

	/**
	  * @author Administrator
	  * @Description: TODO
	  * @param @param param
	  * @param @return  
	  * @throws
	  * @date 2016年3月17日 上午11:37:47
	  */
	@Override
	public int getPlainList4chCount(Map<String, Object> param) {
		
		// TODO Auto-generated method stub
		return orderDetailDao.getPlainList4chCount(param);
		
	}

	/**
	  * @author Administrator
	  * @Description: TODO
	  * @param @param param
	  * @param @return  
	  * @throws
	  * @date 2016年3月17日 上午11:37:47
	  */
	@Override
	public int getSpecialList4chCount(Map<String, Object> param) {
		
		// TODO Auto-generated method stub
		return orderDetailDao.getSpecialList4chCount(param);
		
	}

	/**
	  * @author Administrator
	  * @Description: TODO
	  * @param @param kpxx
	  * @param @param fpmxList  
	  * @throws
	  * @date 2016年3月17日 下午5:57:56
	  */
	@Override
	public void insertFp(Kpxx kpxx, List<Fpmx> fpmxList) {
		Kpxx kpxxt = dao.selectByFpqqlsh(kpxx.getFpqqlsh());
		if(null != kpxxt){
			String f = XmlUtil.random();
			kpxx.setFpqqlsh(f);
			for(Fpmx t:fpmxList){
				t.setFpqqlsh(f);
			}
		}
		logger.info("插入KPXX数据流水号："+kpxx.getFpqqlsh());
		dao.insert(kpxx);
		for(Fpmx tmp:fpmxList){
			fpmxDao.insert(tmp);
		}
	}

}
