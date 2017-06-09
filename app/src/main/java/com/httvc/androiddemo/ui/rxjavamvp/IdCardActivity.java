package com.httvc.androiddemo.ui.rxjavamvp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.httvc.androiddemo.R;
import com.httvc.androiddemo.api.HttpResult;
import com.httvc.androiddemo.pojo.IdCard;
import com.httvc.androiddemo.ui.rxjavamvp.presenter.implPresenter.IdCardPresenterImpl;
import com.httvc.androiddemo.ui.rxjavamvp.presenter.implView.IdCardView;
import com.httvc.androiddemo.utils.CommonUtils;
import com.httvc.androiddemo.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IdCardActivity extends AppCompatActivity implements IdCardView {


    IdCardPresenterImpl idCardPresenter;
    @BindView(R.id.edit_idcard)
    EditText editIdcard;
    @BindView(R.id.im_header)
    ImageView imHeader;
    @BindView(R.id.sc_name)
    EditText scName;
    @BindView(R.id.sc_gender)
    EditText scGender;
    @BindView(R.id.sc_nation)
    EditText scNation;
    @BindView(R.id.sc_date)
    EditText scDate;
    @BindView(R.id.sc_address)
    EditText scAddress;
    @BindView(R.id.sc_identification)
    EditText scIdentification;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_card);
        ButterKnife.bind(this);
        idCardPresenter = new IdCardPresenterImpl(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        idCardPresenter.unsubscrible();

    }

    @Override
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
        }
        mProgressDialog.setMessage("加载中");
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void hidProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }


    @Override
    public void searchId(HttpResult<IdCard> idCardHttpResult) {
        if (idCardHttpResult != null) {
            if ("0".equals(idCardHttpResult.getStatus())) {
                IdCard idCard = idCardHttpResult.getResult();
                if (idCard != null) {
                    String province = idCard.getProvince();
                    String city = idCard.getCity();
                    String town = idCard.getTown();
                    String lastflag = idCard.getLastflag();
                    String sex = idCard.getSex();
                    String birth = idCard.getBirth();
                    String area = idCard.getArea();
                    if (!CommonUtils.isEmpty(province)) {
                        scName.setText(province);
                    }

                    if (!CommonUtils.isEmpty(sex)) {
                        scGender.setText(sex);
                    }
                    if (!CommonUtils.isEmpty(birth)) {
                        scDate.setText(birth);
                    }
                    scAddress.setText(city + town + area);
                }
            } else {
                ToastUtil.show(this, idCardHttpResult.getMsg());
            }
        }
    }

    @OnClick({R.id.btn_search})
    void setOnclick(View view) {
        switch (view.getId()) {

            case R.id.btn_search:
                String editId = editIdcard.getText().toString().trim();
                if (!CommonUtils.isEmpty(editId)) {
                    idCardPresenter.getIdCard(editId);
                } else {
                    ToastUtil.show(this, "请输入身份证号");
                }
                break;
        }
    }
}
