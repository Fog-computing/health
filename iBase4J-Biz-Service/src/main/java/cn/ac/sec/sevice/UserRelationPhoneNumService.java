package cn.ac.sec.sevice;


import cn.ac.sec.dao.BizUserRelationPhoneNumMapper;
import cn.ac.sec.entity.BizUserRelationPhoneNum;
import cn.ac.sec.entity.BizUserRelationPhoneNumExample;
import cn.ac.sec.entity.BizWatch;
import org.ibase4j.core.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserRelationPhoneNumService {


    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private BizUserRelationPhoneNumMapper userPhoneNumMapper;

    @Autowired
    private WatchSettingService watchSettingService;

    @Autowired
    private WatchService watchService;
    /**
     *
     * @param userRelationPhoneNum type 1 紧急联系人 2 快速拨号 3 两者均是
     * @return 0 成功 1已经添加足够的紧急联系人 2 已经添加足够的快速拨号
     */


    public int addUserPhoneNum(BizUserRelationPhoneNum userRelationPhoneNum){
        int checkCounts=checkRelationCounts (userRelationPhoneNum);
        if(checkCounts!=0)throw new BusinessException ("用户相关号码已达上限");
        userPhoneNumMapper.insertSelective (userRelationPhoneNum);
        watchSettingService.setWatchPhoneNum (userRelationPhoneNum.getUser ());
        return 0;
    }

    public String getFirstPhoneNum(Long userId) {
        List<BizUserRelationPhoneNum> relationPhoneNums = getPhoneNumListByUserId (userId);
        for (BizUserRelationPhoneNum userRelationPhoneNum : relationPhoneNums) {
            if (userRelationPhoneNum.getType ().equals ((byte) 1) || userRelationPhoneNum.getType ().equals ((byte) 3))
                return userRelationPhoneNum.getPhoneNum ();
        }
        throw new BusinessException ("没有紧急联系人");
    }

    public List<BizUserRelationPhoneNum> getPhoneNumListByUserId(Long userId){
        BizUserRelationPhoneNumExample example = new BizUserRelationPhoneNumExample ();
        example.createCriteria ().andUserEqualTo (userId);
        return userPhoneNumMapper.selectByExample (example);
    }

    public BizUserRelationPhoneNum getPhoneNumById(Long id){
        return userPhoneNumMapper.selectByPrimaryKey (id);
    }

    public int updateUserPhoneNum(BizUserRelationPhoneNum relationPhoneNum){
        userPhoneNumMapper.updateByPrimaryKeySelective (relationPhoneNum);
//        setWatchPhoneNum (relationPhoneNum.getUser ());
        return 0;
    }

    private int checkRelationCounts(BizUserRelationPhoneNum phoneNum){
        BizUserRelationPhoneNumExample example= new BizUserRelationPhoneNumExample ();
        BizUserRelationPhoneNumExample.Criteria criteria = example.createCriteria ().andUserEqualTo (phoneNum.getUser ());
        List<Byte> typeList =new ArrayList<> ();
        typeList.add ((byte)3);
        int limit;
        if(phoneNum.getType ().equals ((byte)1)
                ||phoneNum.getType ().equals ((byte)3)){
            typeList.add ((byte)1);
            limit=3;
        } else {
            typeList.add ((byte)2);
            limit=4;
        }
        criteria.andTypeIn (typeList);
        int numExist = userPhoneNumMapper.countByExample (example);
        if(limit==3 && numExist>=limit)return 1;
        if (limit == 4 && numExist >= limit) return 2;
        return 0;
    }

    public int deleteUserPhoneNum(Long id){
        BizUserRelationPhoneNum phoneNum= userPhoneNumMapper.selectByPrimaryKey (id);
        long user = phoneNum.getUser ();
        userPhoneNumMapper.deleteByPrimaryKey (id);
        watchSettingService.setWatchPhoneNum (user);
        return 0;

    }



}
