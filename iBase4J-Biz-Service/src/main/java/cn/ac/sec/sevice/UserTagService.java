package cn.ac.sec.sevice;

import cn.ac.sec.dao.BizRelationTagUserMapper;
import cn.ac.sec.dao.BizUserTagMapper;
import cn.ac.sec.entity.BizRelationTagUser;
import cn.ac.sec.entity.BizRelationTagUserExample;
import cn.ac.sec.entity.BizUserTag;
import cn.ac.sec.entity.transport.PageTransport;
import com.baomidou.mybatisplus.plugins.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserTagService extends BaseService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private BizUserTagMapper tagMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private BizRelationTagUserMapper tagUserMapper;

    public List<BizUserTag> getTagList(){
        return tagMapper.selectListWithDetail ();
    }

    public int updateTag(BizUserTag userTag){
        return tagMapper.updateByPrimaryKeySelective (userTag);
    }

    public int addTag(BizUserTag userTag){
        return tagMapper.insertSelective (userTag);
    }

    public int deleteTag(Integer id){
        return tagMapper.deleteByPrimaryKey (id);
    }

    public int deleteUserTag(Long id){
        return tagUserMapper.deleteByPrimaryKey (id);
    }

    public Page<BizUserTag> getTagPage(PageTransport transport){
        Page<BizUserTag> pager;
        pager = getPage (transport);
        PageHelper.startPage (pager.getCurrent (),pager.getSize ());
        List<BizUserTag> devices=tagMapper.selectListWithDetail ();
        pager.setRecords (devices);
        pager.setTotal (devices.size ());
        pager.setTotal ((int)((com.github.pagehelper.Page)devices).getTotal ());
        return pager;
    }

    public List<BizUserTag> getTagListWithUser(Long userId){
        return tagMapper.getTagsByUserId(userId);
    }

    public int addRelationTagsList(List<BizRelationTagUser> relationTagUsers){
        return tagUserMapper.addRelationTagsList (relationTagUsers);
    }
    public BizRelationTagUser addRelationTag(BizRelationTagUser relationTagUser){
        tagUserMapper.insertSelective (relationTagUser);
        return relationTagUser;
    }

    public List<BizUserTag> getTagWithoutUser(Long userId){
        return tagMapper.getTagWithoutUser(userId);
    }

    public Map<String,List<BizUserTag>> tagMap(Long userId){
        Map<String,List<BizUserTag>> resultMap=new HashMap<> ();
        resultMap.put ("userTag",tagMapper.getTagsByUserId (userId));
        resultMap.put ("totalTag", tagMapper.selectByExample (null));
        return resultMap;
    }

    public int deleteUserTagRelationByUserId(BizRelationTagUser tagUser) {
        BizRelationTagUserExample example = new BizRelationTagUserExample ();
        example.createCriteria ().andTagEqualTo (tagUser.getTag ()).andUserEqualTo (tagUser.getUser ());
        return tagUserMapper.deleteByExample (example);
    }
    public int deleteUserTagRelationById(Long id){
        return tagUserMapper.deleteByPrimaryKey (id);
    }

    public BizUserTag getUserTagById(Integer id){
        return tagMapper.selectByPrimaryKey (id);
    }

}
