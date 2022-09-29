package vn.co.honda.hondacrm.btu.Model.message.register.response;


import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import vn.co.honda.hondacrm.btu.Constants.message.ParamTag;
import vn.co.honda.hondacrm.btu.Model.message.common.VehicleParam;
import vn.co.honda.hondacrm.btu.Utils.StringUtils;

public	class VehicleRegistrationResponseMessage {
	private int mRegistrationType;
	private int mRegistrationResult;
	private int mRegistrationResultCause;
	private List<String> mUserNameList;
	
	public VehicleRegistrationResponseMessage(List<VehicleParam> paramList) {
		for (VehicleParam param : paramList) {
			if (param == null) {
				continue;
			}
			switch (param.mTag) {
			case ParamTag.REGISTRATION_TYPE:
				ByteBuffer typeBuf = ByteBuffer.wrap(param.mValue);
				mRegistrationType = typeBuf.get();
				break;
			case ParamTag.REGISTRATION_RESULT:
				ByteBuffer resultBuf = ByteBuffer.wrap(param.mValue);
				mRegistrationResult = resultBuf.get();
				break;
			case ParamTag.RESULT_CAUSE:
				ByteBuffer causeBuf = ByteBuffer.wrap(param.mValue);
				mRegistrationResultCause = causeBuf.get();
				break;
			case ParamTag.USER_NAME:
				if (mUserNameList == null) {
					mUserNameList = new ArrayList<>();
				}
				String userName = StringUtils.convertBytesToString(param.mValue, "");
				if (userName != null) {
					mUserNameList.add(userName);
				}
				break;
			default :
				break;
			}
		}
	}

	public int getRegistrationType() {
		return mRegistrationType;
	}

	public int getRegistrationResult() {
		return mRegistrationResult;
	}

	public int getRegistrationResultCause() {
		return mRegistrationResultCause;
	}

	public List<String> getUserNameList() {
		return mUserNameList;
	}
}
