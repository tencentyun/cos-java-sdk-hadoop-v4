package com.qcloud.cos.request;

import com.qcloud.cos.common_utils.CommonParamCheckUtils;
import com.qcloud.cos.exception.ParamException;
import com.qcloud.cos.meta.InsertOnly;

/**
 * @author chengwu 移动文件请求
 */
public class CopyFileRequest extends AbstractBaseRequest {

	private String dstCosPath = "";
	// 移动文件, 如果目的路径已有文件存在, 默认不覆盖
	private InsertOnly insertOnly = InsertOnly.OVER_WRITE;

	public CopyFileRequest(String bucketName, String srcCosPath, String dstCosPath) {
		super(bucketName, srcCosPath);
		this.dstCosPath = dstCosPath;
	}

	public String getDstCosPath() {
		return dstCosPath;
	}

	public void setDstCosPath(String dstCosPath) {
		this.dstCosPath = dstCosPath;
	}

	public InsertOnly getInsertOnly() {
		return insertOnly;
	}

	public void setInsertOnly(InsertOnly insertOnly) {
		this.insertOnly = insertOnly;
	}

	@Override
	public void check_param() throws ParamException {
		super.check_param();
		CommonParamCheckUtils.AssertLegalCosFilePath(this.getCosPath());
		CommonParamCheckUtils.AssertLegalCosFilePath(this.dstCosPath);
		CommonParamCheckUtils.AssertNotNull("InsertOnly", this.insertOnly);
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
