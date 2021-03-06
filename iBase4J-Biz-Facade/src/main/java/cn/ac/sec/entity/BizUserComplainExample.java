package cn.ac.sec.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BizUserComplainExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table biz_user_complain
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table biz_user_complain
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table biz_user_complain
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user_complain
     *
     * @mbggenerated
     */
    public BizUserComplainExample() {
        oredCriteria = new ArrayList<Criteria> ();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user_complain
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user_complain
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user_complain
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user_complain
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user_complain
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user_complain
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add (criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user_complain
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal ();
        oredCriteria.add (criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user_complain
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal ();
        if (oredCriteria.size () == 0) {
            oredCriteria.add (criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user_complain
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria ();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user_complain
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear ();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table biz_user_complain
     *
     * @mbggenerated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super ();
            criteria = new ArrayList<Criterion> ();
        }

        public boolean isValid() {
            return criteria.size () > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException ("Value for condition cannot be null");
            }
            criteria.add (new Criterion (condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException ("Value for " + property + " cannot be null");
            }
            criteria.add (new Criterion (condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException ("Between values for " + property + " cannot be null");
            }
            criteria.add (new Criterion (condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion ("id_ is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion ("id_ is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion ("id_ =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion ("id_ <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion ("id_ >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion ("id_ >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion ("id_ <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion ("id_ <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion ("id_ in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion ("id_ not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion ("id_ between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion ("id_ not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andComplainContentIsNull() {
            addCriterion ("complain_content is null");
            return (Criteria) this;
        }

        public Criteria andComplainContentIsNotNull() {
            addCriterion ("complain_content is not null");
            return (Criteria) this;
        }

        public Criteria andComplainContentEqualTo(String value) {
            addCriterion ("complain_content =", value, "complainContent");
            return (Criteria) this;
        }

        public Criteria andComplainContentNotEqualTo(String value) {
            addCriterion ("complain_content <>", value, "complainContent");
            return (Criteria) this;
        }

        public Criteria andComplainContentGreaterThan(String value) {
            addCriterion ("complain_content >", value, "complainContent");
            return (Criteria) this;
        }

        public Criteria andComplainContentGreaterThanOrEqualTo(String value) {
            addCriterion ("complain_content >=", value, "complainContent");
            return (Criteria) this;
        }

        public Criteria andComplainContentLessThan(String value) {
            addCriterion ("complain_content <", value, "complainContent");
            return (Criteria) this;
        }

        public Criteria andComplainContentLessThanOrEqualTo(String value) {
            addCriterion ("complain_content <=", value, "complainContent");
            return (Criteria) this;
        }

        public Criteria andComplainContentLike(String value) {
            addCriterion ("complain_content like", value, "complainContent");
            return (Criteria) this;
        }

        public Criteria andComplainContentNotLike(String value) {
            addCriterion ("complain_content not like", value, "complainContent");
            return (Criteria) this;
        }

        public Criteria andComplainContentIn(List<String> values) {
            addCriterion ("complain_content in", values, "complainContent");
            return (Criteria) this;
        }

        public Criteria andComplainContentNotIn(List<String> values) {
            addCriterion ("complain_content not in", values, "complainContent");
            return (Criteria) this;
        }

        public Criteria andComplainContentBetween(String value1, String value2) {
            addCriterion ("complain_content between", value1, value2, "complainContent");
            return (Criteria) this;
        }

        public Criteria andComplainContentNotBetween(String value1, String value2) {
            addCriterion ("complain_content not between", value1, value2, "complainContent");
            return (Criteria) this;
        }

        public Criteria andComplainUserIsNull() {
            addCriterion ("complain_user is null");
            return (Criteria) this;
        }

        public Criteria andComplainUserIsNotNull() {
            addCriterion ("complain_user is not null");
            return (Criteria) this;
        }

        public Criteria andComplainUserEqualTo(Long value) {
            addCriterion ("complain_user =", value, "complainUser");
            return (Criteria) this;
        }

        public Criteria andComplainUserNotEqualTo(Long value) {
            addCriterion ("complain_user <>", value, "complainUser");
            return (Criteria) this;
        }

        public Criteria andComplainUserGreaterThan(Long value) {
            addCriterion ("complain_user >", value, "complainUser");
            return (Criteria) this;
        }

        public Criteria andComplainUserGreaterThanOrEqualTo(Long value) {
            addCriterion ("complain_user >=", value, "complainUser");
            return (Criteria) this;
        }

        public Criteria andComplainUserLessThan(Long value) {
            addCriterion ("complain_user <", value, "complainUser");
            return (Criteria) this;
        }

        public Criteria andComplainUserLessThanOrEqualTo(Long value) {
            addCriterion ("complain_user <=", value, "complainUser");
            return (Criteria) this;
        }

        public Criteria andComplainUserIn(List<Long> values) {
            addCriterion ("complain_user in", values, "complainUser");
            return (Criteria) this;
        }

        public Criteria andComplainUserNotIn(List<Long> values) {
            addCriterion ("complain_user not in", values, "complainUser");
            return (Criteria) this;
        }

        public Criteria andComplainUserBetween(Long value1, Long value2) {
            addCriterion ("complain_user between", value1, value2, "complainUser");
            return (Criteria) this;
        }

        public Criteria andComplainUserNotBetween(Long value1, Long value2) {
            addCriterion ("complain_user not between", value1, value2, "complainUser");
            return (Criteria) this;
        }

        public Criteria andComplainDateIsNull() {
            addCriterion ("complain_date is null");
            return (Criteria) this;
        }

        public Criteria andComplainDateIsNotNull() {
            addCriterion ("complain_date is not null");
            return (Criteria) this;
        }

        public Criteria andComplainDateEqualTo(Date value) {
            addCriterion ("complain_date =", value, "complainDate");
            return (Criteria) this;
        }

        public Criteria andComplainDateNotEqualTo(Date value) {
            addCriterion ("complain_date <>", value, "complainDate");
            return (Criteria) this;
        }

        public Criteria andComplainDateGreaterThan(Date value) {
            addCriterion ("complain_date >", value, "complainDate");
            return (Criteria) this;
        }

        public Criteria andComplainDateGreaterThanOrEqualTo(Date value) {
            addCriterion ("complain_date >=", value, "complainDate");
            return (Criteria) this;
        }

        public Criteria andComplainDateLessThan(Date value) {
            addCriterion ("complain_date <", value, "complainDate");
            return (Criteria) this;
        }

        public Criteria andComplainDateLessThanOrEqualTo(Date value) {
            addCriterion ("complain_date <=", value, "complainDate");
            return (Criteria) this;
        }

        public Criteria andComplainDateIn(List<Date> values) {
            addCriterion ("complain_date in", values, "complainDate");
            return (Criteria) this;
        }

        public Criteria andComplainDateNotIn(List<Date> values) {
            addCriterion ("complain_date not in", values, "complainDate");
            return (Criteria) this;
        }

        public Criteria andComplainDateBetween(Date value1, Date value2) {
            addCriterion ("complain_date between", value1, value2, "complainDate");
            return (Criteria) this;
        }

        public Criteria andComplainDateNotBetween(Date value1, Date value2) {
            addCriterion ("complain_date not between", value1, value2, "complainDate");
            return (Criteria) this;
        }

        public Criteria andProcessUserIsNull() {
            addCriterion ("process_user is null");
            return (Criteria) this;
        }

        public Criteria andProcessUserIsNotNull() {
            addCriterion ("process_user is not null");
            return (Criteria) this;
        }

        public Criteria andProcessUserEqualTo(Long value) {
            addCriterion ("process_user =", value, "processUser");
            return (Criteria) this;
        }

        public Criteria andProcessUserNotEqualTo(Long value) {
            addCriterion ("process_user <>", value, "processUser");
            return (Criteria) this;
        }

        public Criteria andProcessUserGreaterThan(Long value) {
            addCriterion ("process_user >", value, "processUser");
            return (Criteria) this;
        }

        public Criteria andProcessUserGreaterThanOrEqualTo(Long value) {
            addCriterion ("process_user >=", value, "processUser");
            return (Criteria) this;
        }

        public Criteria andProcessUserLessThan(Long value) {
            addCriterion ("process_user <", value, "processUser");
            return (Criteria) this;
        }

        public Criteria andProcessUserLessThanOrEqualTo(Long value) {
            addCriterion ("process_user <=", value, "processUser");
            return (Criteria) this;
        }

        public Criteria andProcessUserIn(List<Long> values) {
            addCriterion ("process_user in", values, "processUser");
            return (Criteria) this;
        }

        public Criteria andProcessUserNotIn(List<Long> values) {
            addCriterion ("process_user not in", values, "processUser");
            return (Criteria) this;
        }

        public Criteria andProcessUserBetween(Long value1, Long value2) {
            addCriterion ("process_user between", value1, value2, "processUser");
            return (Criteria) this;
        }

        public Criteria andProcessUserNotBetween(Long value1, Long value2) {
            addCriterion ("process_user not between", value1, value2, "processUser");
            return (Criteria) this;
        }

        public Criteria andProcessDateIsNull() {
            addCriterion ("process_date is null");
            return (Criteria) this;
        }

        public Criteria andProcessDateIsNotNull() {
            addCriterion ("process_date is not null");
            return (Criteria) this;
        }

        public Criteria andProcessDateEqualTo(Date value) {
            addCriterion ("process_date =", value, "processDate");
            return (Criteria) this;
        }

        public Criteria andProcessDateNotEqualTo(Date value) {
            addCriterion ("process_date <>", value, "processDate");
            return (Criteria) this;
        }

        public Criteria andProcessDateGreaterThan(Date value) {
            addCriterion ("process_date >", value, "processDate");
            return (Criteria) this;
        }

        public Criteria andProcessDateGreaterThanOrEqualTo(Date value) {
            addCriterion ("process_date >=", value, "processDate");
            return (Criteria) this;
        }

        public Criteria andProcessDateLessThan(Date value) {
            addCriterion ("process_date <", value, "processDate");
            return (Criteria) this;
        }

        public Criteria andProcessDateLessThanOrEqualTo(Date value) {
            addCriterion ("process_date <=", value, "processDate");
            return (Criteria) this;
        }

        public Criteria andProcessDateIn(List<Date> values) {
            addCriterion ("process_date in", values, "processDate");
            return (Criteria) this;
        }

        public Criteria andProcessDateNotIn(List<Date> values) {
            addCriterion ("process_date not in", values, "processDate");
            return (Criteria) this;
        }

        public Criteria andProcessDateBetween(Date value1, Date value2) {
            addCriterion ("process_date between", value1, value2, "processDate");
            return (Criteria) this;
        }

        public Criteria andProcessDateNotBetween(Date value1, Date value2) {
            addCriterion ("process_date not between", value1, value2, "processDate");
            return (Criteria) this;
        }

        public Criteria andProcessContentIsNull() {
            addCriterion ("process_content is null");
            return (Criteria) this;
        }

        public Criteria andProcessContentIsNotNull() {
            addCriterion ("process_content is not null");
            return (Criteria) this;
        }

        public Criteria andProcessContentEqualTo(String value) {
            addCriterion ("process_content =", value, "processContent");
            return (Criteria) this;
        }

        public Criteria andProcessContentNotEqualTo(String value) {
            addCriterion ("process_content <>", value, "processContent");
            return (Criteria) this;
        }

        public Criteria andProcessContentGreaterThan(String value) {
            addCriterion ("process_content >", value, "processContent");
            return (Criteria) this;
        }

        public Criteria andProcessContentGreaterThanOrEqualTo(String value) {
            addCriterion ("process_content >=", value, "processContent");
            return (Criteria) this;
        }

        public Criteria andProcessContentLessThan(String value) {
            addCriterion ("process_content <", value, "processContent");
            return (Criteria) this;
        }

        public Criteria andProcessContentLessThanOrEqualTo(String value) {
            addCriterion ("process_content <=", value, "processContent");
            return (Criteria) this;
        }

        public Criteria andProcessContentLike(String value) {
            addCriterion ("process_content like", value, "processContent");
            return (Criteria) this;
        }

        public Criteria andProcessContentNotLike(String value) {
            addCriterion ("process_content not like", value, "processContent");
            return (Criteria) this;
        }

        public Criteria andProcessContentIn(List<String> values) {
            addCriterion ("process_content in", values, "processContent");
            return (Criteria) this;
        }

        public Criteria andProcessContentNotIn(List<String> values) {
            addCriterion ("process_content not in", values, "processContent");
            return (Criteria) this;
        }

        public Criteria andProcessContentBetween(String value1, String value2) {
            addCriterion ("process_content between", value1, value2, "processContent");
            return (Criteria) this;
        }

        public Criteria andProcessContentNotBetween(String value1, String value2) {
            addCriterion ("process_content not between", value1, value2, "processContent");
            return (Criteria) this;
        }

        public Criteria andPhoneNumIsNull() {
            addCriterion ("phone_num is null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumIsNotNull() {
            addCriterion ("phone_num is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumEqualTo(String value) {
            addCriterion ("phone_num =", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotEqualTo(String value) {
            addCriterion ("phone_num <>", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumGreaterThan(String value) {
            addCriterion ("phone_num >", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumGreaterThanOrEqualTo(String value) {
            addCriterion ("phone_num >=", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLessThan(String value) {
            addCriterion ("phone_num <", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLessThanOrEqualTo(String value) {
            addCriterion ("phone_num <=", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLike(String value) {
            addCriterion ("phone_num like", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotLike(String value) {
            addCriterion ("phone_num not like", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumIn(List<String> values) {
            addCriterion ("phone_num in", values, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotIn(List<String> values) {
            addCriterion ("phone_num not in", values, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumBetween(String value1, String value2) {
            addCriterion ("phone_num between", value1, value2, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotBetween(String value1, String value2) {
            addCriterion ("phone_num not between", value1, value2, "phoneNum");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table biz_user_complain
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super ();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table biz_user_complain
     *
     * @mbggenerated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        protected Criterion(String condition) {
            super ();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super ();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this (condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super ();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this (condition, value, secondValue, null);
        }

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }
    }
}