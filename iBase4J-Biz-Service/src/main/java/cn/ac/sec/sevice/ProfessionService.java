package cn.ac.sec.sevice;

import cn.ac.sec.dao.BizProfessionMapper;
import cn.ac.sec.entity.BizProfession;
import cn.ac.sec.entity.BizProfessionExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProfessionService extends BaseService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private BizProfessionMapper professionMapper;

    public int createProfession(BizProfession profession){
        return   professionMapper.insertSelective (profession);
    }

    public boolean checkProfession(Long userId) {
        BizProfessionExample example = new BizProfessionExample ();
        example.createCriteria ().andUserIdEqualTo (userId).andStatusEqualTo ((byte) 0);
        List<BizProfession> professions = professionMapper.selectByExample (example);
        return professions != null && professions.size () != 0;
    }

    public int updateProfession(BizProfession profession) {
        return professionMapper.updateByPrimaryKeySelective (profession);
    }

    public BizProfession getUnprocessedProfession(Long userId) {
        BizProfessionExample example = new BizProfessionExample ();
        example.createCriteria ().andUserIdEqualTo (userId).andStatusEqualTo ((byte) 0);
        List<BizProfession> professions = professionMapper.selectByExample (example);
        if (professions != null && professions.size () != 0) return professions.get (0);
        else return null;
    }

    public List<BizProfession> getProfessionList(Long userId, Date start, Date end) {
        BizProfessionExample example = new BizProfessionExample ();
        example.createCriteria ().andUserIdEqualTo (userId).andProcessDateBetween (start, end);
        List<BizProfession> professions = professionMapper.selectByExample (example);
        if (professions != null && professions.size () != 0) return professions;
        else return null;
    }

    public List<BizProfession> getLatestProfession(Long userId) {
        Date now = new Date ();
        Date before = new Date (now.getTime () - 1000 * 60 * 60 * 24 * 15);
        return getProfessionList (userId, before, now);
    }

}
