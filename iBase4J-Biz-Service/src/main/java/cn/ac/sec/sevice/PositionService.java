package cn.ac.sec.sevice;

import cn.ac.sec.dao.BizMessagePositionMapper;
import cn.ac.sec.entity.BizMessagePosition;
import cn.ac.sec.entity.BizMessagePositionExample;
import com.github.pagehelper.PageHelper;
import org.ibase4j.core.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PositionService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private BizMessagePositionMapper positionMapper;

    public BizMessagePosition getLatestPosition(Long userId){
        BizMessagePositionExample example = new BizMessagePositionExample ();
        example.createCriteria ().andUserEqualTo (userId);
        example.setOrderByClause ("time_ desc");
        PageHelper.startPage (0,1);
        List<BizMessagePosition> positions=positionMapper.selectByExample (example);
        if (positions == null || positions.size () == 0) throw new BusinessException ("该用户没有位置信息");
        return positions.get (0);
    }

    public List<BizMessagePosition> getMessageList(Date startTime,Date endTime,Long userId){
        BizMessagePositionExample example=new BizMessagePositionExample ();
        example.createCriteria ().andUserEqualTo (userId).andTimeBetween (startTime,endTime);
        return positionMapper.selectByExample (example);
    }


}
