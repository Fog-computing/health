package cn.ac.sec.sevice;

import cn.ac.sec.dao.BizPhoneCallMapper;
import cn.ac.sec.entity.BizPhoneCallWithBLOBs;
import cn.ac.sec.entity.BizUser;
import cn.ac.sec.entity.transport.PageTransport;
import com.baomidou.mybatisplus.plugins.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserCallService extends BaseService {

    @Autowired
    BizUserService userService;
    @Autowired
    private BizPhoneCallMapper phoneCallMapper;

    public Page<Map<String, Object>> getPhoneCallPage(PageTransport transport) {
        Page<Map<String, Object>> pager;
        pager = getPage (transport);
        PageHelper.startPage (pager.getCurrent (), pager.getSize ());
        List<Map<String, Object>> callList = phoneCallMapper.getCallList ("%" + transport.getKeyword () + "%");
        pager.setRecords (callList);
        pager.setTotal (callList.size ());
        pager.setTotal ((int) ((com.github.pagehelper.Page) callList).getTotal ());
        return pager;
    }

    public int addPhoneCall(BizPhoneCallWithBLOBs phoneCallWithBLOBs) {
        BizUser user = new BizUser ();
        user.setPhoneNum (phoneCallWithBLOBs.getConsumer ().toString ());
        userService.getUserByUniqueCol (user);
        phoneCallWithBLOBs.setConsumer (userService.getUserByUniqueCol (user).getId ());
        return phoneCallMapper.insertSelective (phoneCallWithBLOBs);
    }

    public BizPhoneCallWithBLOBs getPhoneCallDetail(Long id) {
        BizPhoneCallWithBLOBs phoneCallWithBLOBs = phoneCallMapper.selectByPrimaryKey (id);
        phoneCallWithBLOBs.setConsumer (Long.parseLong (userService.getUserById
                (phoneCallWithBLOBs.getConsumer ()).getPhoneNum ()));
        return phoneCallWithBLOBs;
    }

    public int deletePhoneCall(Long id) {
        return phoneCallMapper.deleteByPrimaryKey (id);
    }
}
