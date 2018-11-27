package cn.ac.sec.sevice;

import cn.ac.sec.dao.BizUserComplainMapper;
import cn.ac.sec.entity.BizUserComplain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserComplainService extends BaseService {
    @Autowired
    BizUserComplainMapper complainMapper;

    public int addUserComplain(BizUserComplain userComplain) {
        return complainMapper.insertSelective (userComplain);
    }
}
