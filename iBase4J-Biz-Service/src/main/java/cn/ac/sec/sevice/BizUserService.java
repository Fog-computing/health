package cn.ac.sec.sevice;


import cn.ac.sec.dao.BizRelationUserMapper;
import cn.ac.sec.dao.BizUserMapper;
import cn.ac.sec.entity.*;
import cn.ac.sec.entity.transport.PageTransport;
import cn.ac.sec.protocol.message.OutMessage;
import cn.ac.sec.util.CommonUtil;
import cn.ac.sec.util.PushExample;
import com.baomidou.mybatisplus.plugins.Page;
import com.github.pagehelper.PageHelper;
import org.ibase4j.core.exception.BusinessException;
import org.ibase4j.core.exception.LoginException;
import org.ibase4j.core.util.SecurityUtil;
import org.ibase4j.model.ChartData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class BizUserService extends BaseService<BizUser>{
    @Autowired
    private BizUserMapper userMapper;

    @Autowired
    private UserTagService tagService;

    @Autowired
    private UserRelationPhoneNumService phoneNumService;

    @Autowired
    private AlarmService alarmService;

    @Autowired
    private WatchSettingService watchSettingService;

    @Autowired
    private WatchService watchService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ProfessionService professionService;

    @Autowired
    private BizRelationUserMapper relationUserMapper;

    @Autowired
    UserSubscriptionService userSubscriptionService;


    public List<BizUser> getUserList() {
        return userMapper.selectByExample (new BizUserExample ());
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public BizUser addUser(BizUser user){
        if (checkPhoneExist (user)) {
            user.setUserType ((byte) 3);
            updateUserByPhoneNum (user);
            return user;
        }
        userMapper.insertSelective (user);
        if (!user.getUserType ().equals ((byte) 2)) userSubscriptionService.updateSubscription (user);
        return user;
    }

    public BizUser bindUser(Long userId, String phoneNum) {
        BizRelationUser relationUser = new BizRelationUser ();
        BizUser user = new BizUser ();
        user.setPhoneNum (phoneNum);
        BizUser parent = getUserByUniqueCol (user);
        if (parent.getUserType ().equals ((byte) 2)) throw new BusinessException ("该用户无法绑定");
        relationUser.setParent (getUserByUniqueCol (user).getId ());
        relationUser.setChild (userId);
        relationUserMapper.insertSelective (relationUser);
        BizUser child = userMapper.selectByPrimaryKey (userId);
        return child;
    }
    public BizUser getUserDetail(Long userId) {
        return getUserById (userId);
    }

    /**
     * @param userId  用户id
     * @param dateMap map key start : 获取用户档案中报警信息、专业护理信息的开始时间
     *                key end: 获取用户档案中报警信息、专业护理信息的结束时间
     * @return
     */
    public Map<String, Object> userFile(Long userId, Map<String, Date> dateMap) {
        Map<String,Object> resultMap=new HashMap<> ();
        Date start = dateMap.get ("start");
        Date end = dateMap.get ("end");
        BizUser user = getUserById (userId);
        resultMap.put ("user",user);
        resultMap.put ("tags",tagService.getTagListWithUser (userId));
        List<BizUserRelationPhoneNum> phoneNumList=phoneNumService.getPhoneNumListByUserId (userId);
        resultMap.put ("phoneNum",phoneNumList);
        resultMap.put ("devices",deviceService.getDeviceListByUser (userId));
        resultMap.put ("watch",watchService.getWatchByUser (userId));
        resultMap.put ("alarms", alarmService.getAlarmsByDate (userId, start, end));
        resultMap.put ("profession", professionService.getProfessionList (userId, start, end));
        return resultMap;
    }

    /**
     *
     * @param userId 用户id
     * @return 用户电话列表
     */
    public List<BizUserRelationPhoneNum> getUserPhoneNum(Long userId) {
        return phoneNumService.getPhoneNumListByUserId (userId);
    }

    public String getUserSOSPhoneNum(Long userId) {
        return phoneNumService.getFirstPhoneNum (userId);
    }
    /**
     * @param transport 分页信息
     * @return 根据用户是否有专业护理信息获取用户列表，用户列表中包含用户标签信息
     */
    public Page<Map<String, Object>> professionUserWithTags(PageTransport transport) {
        Page<BizUser> users = getProfessionUserListWithTag (transport);
        List<Map<String,Object>> resultList =new ArrayList<> ();
        getTagList (users, resultList);
        Page<Map<String, Object>> userTagPage = new Page<> ();
        userTagPage.setRecords (resultList);
        userTagPage.setTotal (users.getTotal ());
        userTagPage.setCurrent (users.getCurrent ());
        userTagPage.setSize (users.getSize ());
        return userTagPage;
    }

    /**
     * @param users      用户分页列表
     * @param resultList 添加了用户标签的结果列表
     */
    private void getTagList(Page<BizUser> users, List<Map<String, Object>> resultList) {
        for (BizUser user : users.getRecords ()){
            Map<String,Object> resultMap=new HashMap<> ();
            resultMap.put ("user",user);
            resultMap.put ("tags",tagService.getTagListWithUser (user.getId ()));
            resultList.add (resultMap);
        }
    }


    /**
     *
     * @param transport 分页信息
     * @return 包含标签的老人端用户信息列表
     */
    public Page<Map<String, Object>> userWithTags(PageTransport transport) {
        Page<BizUser> users = getUserListWithTag (transport);
        List<Map<String, Object>> resultList = new ArrayList<> ();
        getTagList (users, resultList);
        Page<Map<String,Object>> userTagPage=new Page<> ();
        userTagPage.setRecords (resultList);
        userTagPage.setTotal (users.getTotal ());
        userTagPage.setCurrent (users.getCurrent ());
        userTagPage.setSize (users.getSize ());
        return userTagPage;
    }

    /**
     *
     * @param userId 用户id
     *               用于软删除用户
     * @return 更新用户结果
     */
    public int deleteUser(Long userId){
        BizUser user = new BizUser ();
        user.setId (userId);
        user.setDeleteStatus (true);
        return userMapper.updateByPrimaryKeySelective (user);
    }

    /**
     *
     * @param user 用户信息
     * @return 更新后的用户信息
     */
    public BizUser updateUserByPrimaryKey(BizUser user){
        userMapper.updateByPrimaryKeySelective (user);
        if (user.getSubTime () != null) userSubscriptionService.updateSubscription (user);
        return user;
    }

    /**
     *
     * @param user 用户信息
     * @return 根据账号修改用户信息
     */
    public int updateUser(BizUser user){
        BizUserExample bizUserExample = new BizUserExample ();
        bizUserExample.createCriteria ()
                .andAccountEqualTo (user.getAccount ());
        BizUser tempUser=userMapper.selectByExample (bizUserExample).get (0);
        user.setId (tempUser.getId ());
        if (checkPhoneExist (user)) throw new BusinessException ("用户号码已经存在");
        return userMapper.updateByPrimaryKeySelective (user);
    }

    /**
     *
     * @param transport 分页信息
     * @return 用户信息列表
     */
    public Page<BizUser> getUserList(PageTransport transport){
        Page<BizUser> pager;
        pager = getPage (transport);
        PageHelper.startPage (pager.getCurrent (),pager.getSize ());
        List<BizUser> users=userMapper.listSearch ("%"+transport.getKeyword ()+"%");
        pager.setRecords (users);
        pager.setTotal ((int)((com.github.pagehelper.Page)users).getTotal ());
        return pager;
    }

    public Page<BizUser> getUserListAll(PageTransport transport) {
        Page<BizUser> pager;
        pager = getPage (transport);
        PageHelper.startPage (pager.getCurrent (), pager.getSize ());
        List<BizUser> users = userMapper.listSearchAll ("%" + transport.getKeyword () + "%");
        pager.setRecords (users);
        pager.setTotal ((int) ((com.github.pagehelper.Page) users).getTotal ());
        return pager;
    }

    public Page<BizUser> getUserListWithTag(PageTransport transport){
        Page<BizUser> pager;
        pager = getPage (transport);
        PageHelper.startPage (pager.getCurrent (),pager.getSize ());
        List<BizUser> users=userMapper.getUserByTagOrOther ("%"+transport.getKeyword ()+"%");
        pager.setRecords (users);
        pager.setTotal ((int)((com.github.pagehelper.Page)users).getTotal ());
        return pager;
    }

    public Page<BizUser> getProfessionUserListWithTag(PageTransport transport) {
        Page<BizUser> pager;
        pager = getPage (transport);
        PageHelper.startPage (pager.getCurrent (), pager.getSize ());
        List<BizUser> users = userMapper.getUserByTagOrOtherByProfession ("%" + transport.getKeyword () + "%");
        pager.setRecords (users);
        pager.setTotal ((int) ((com.github.pagehelper.Page) users).getTotal ());
        return pager;
    }


    public List<BizUser> getUsersByPhoneNum(BizUser user){
        BizUserExample example= new BizUserExample ();
        example.createCriteria ().andPhoneNumEqualTo (user.getPhoneNum ());
        return userMapper.selectByExample (example);
    }

    public boolean checkPhoneExist(BizUser user){
        List<BizUser> list = getUsersByPhoneNum (user);
        return list != null && list.size () != 0 && (list.get (0).getUserType ().equals ((byte) 2) || list.get (0).getUserType ().equals ((byte) 3));
    }


    public BizUser addUserWithPhoneNum(BizUser user){
        if(checkPhoneExist (user))return updateUserByPhoneNum (user);
        return addUser (user);
    }

    public BizUser appLogin(BizUser user) {
        BizUser origin = getUserByUniqueCol (user);
        if (origin.getPassword ().equals (SecurityUtil.encryptPassword (user.getPassword ()))) return origin;
        else throw new LoginException ("密码错误");
    }



    public BizUser getUserById(Long userId){
        BizUser user = userMapper.selectByPrimaryKey (userId);
        user.setSubscription (userSubscriptionService.getLastSubscription (userId));
        return user;
    }


    public BizUser updateUserByPhoneNum(BizUser user){
        return updateUserByUniqueCol (user);
    }

    public BizUser updateUserByUniqueCol(BizUser user){
        userMapper.updateByExampleSelective (user, getUniqueExample (user));
        return user;
    }

    public BizUser getUserByUniqueCol(BizUser user){
        List<BizUser> list = userMapper.selectByExample (getUniqueExample (user));
        if (list == null || list.size () == 0) throw new LoginException ("用户不存在");
        return list.get (0);
    }

    private BizUserExample getUniqueExample(BizUser user){
        BizUserExample example= new BizUserExample ();
        BizUserExample.Criteria criteria=example.createCriteria ();
        criteria.andDeleteStatusEqualTo (false);
        if(user.getIdCard ()!=null&&!user.getIdCard ().equals ("")) criteria.andIdCardEqualTo (user.getIdCard ());
        else if(user.getPhoneNum ()!=null &&!user.getPhoneNum ().equals (""))criteria.andPhoneNumEqualTo (user.getPhoneNum ());
        else if (user.getAccount () != null && !user.getAccount ().equals (""))
            criteria.andAccountEqualTo (user.getAccount ());
        return example;
    }

    /**
     *
     * @param userId 用户id
     * 同步用户信息
     */
    public void updateWatchUserInfo(Long userId){
        BizUser user = getUserById (userId);
        Map<String,Object> params = new HashMap<> ();
        params.put ("userId",user.getId ());
        params.put ("type", OutMessage.INFOSET);
        List<String> paramsList= new ArrayList<> ();
        paramsList.add (""+ CommonUtil.getAge (user.getBirthday ()));
        paramsList.add (""+user.getHeight ());
        paramsList.add ("60");
        paramsList.add (""+user.getWeight ());
        if(user.getGender ())paramsList.add ("1");
        else paramsList.add ("0");
        params.put ("param",paramsList);
        watchSettingService.watchOperate (params);
    }

    public int deleteUserTag(Long id){
        return tagService.deleteUserTagRelationById (id);
    }

    public Object addUserTag(BizRelationTagUser relationTagUser){
        return tagService.addRelationTag (relationTagUser);
    }


    public List<BizUser> getUserByChild(Long userId) {
        return userMapper.getUserListByChild (userId);
    }

    public List<BizUser> getUserByParent(Long userId) {
        return userMapper.getUserListByParent (userId);
    }

    public void pushMessage(BizUser user){
        PushExample.loginPushWithCallback(user.getToken(),"异地登录","" +
                "您的账号：" + user.getAccount() + "在另一设备上登录，若非本人操作，请及时修改密码");
    }
    /**
     * 按月方式统计注册用户数量
     */
    public List<ChartData> getRegisterCountByMonth(String time){
        return userMapper.getRegisterCountByMonth(time);
    }
    /**
     * 按周方式统计注册用户数量
     * 星期由周一开始
     */
    public List<ChartData> getRegisterCountByWeek(String time){
        return userMapper.getRegisterCountByWeek(time);
    }
    /**
     * 按日方式统计注册用户数量
     *
     */
    public List<ChartData> getRegisterCountByDay(List<Integer> list){
        return userMapper.getRegisterCountByDay(list.get(0),list.get(1));
    }

    /**
     * 统计老人不同性别的数量
     */
    public Long getMaleAmount(int gender){
        return userMapper.findMaleAmount(gender);
    }

    /**
     * 统计不同年龄的老人用户数量
     */
    public Long getAgeAmount(List<Integer> list){
        return userMapper.findAgeAmount(list.get(0),list.get(1));
    }

    public Long getOtherAgeAmount(){
        return userMapper.findOtherAgeAmount();
    }

    /**
     * 统计不同标签的老人用户数量
     */
    public Long getTagAmount(Integer tagId){
        return userMapper.findTagAmount(tagId);
    }
}
