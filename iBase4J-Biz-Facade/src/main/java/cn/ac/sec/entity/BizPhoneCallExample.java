package cn.ac.sec.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BizPhoneCallExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table biz_phone_call
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table biz_phone_call
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table biz_phone_call
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_phone_call
     *
     * @mbggenerated
     */
    public BizPhoneCallExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_phone_call
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_phone_call
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_phone_call
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_phone_call
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_phone_call
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_phone_call
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_phone_call
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_phone_call
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_phone_call
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_phone_call
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table biz_phone_call
     *
     * @mbggenerated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
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

        public Criteria andConsumerIsNull() {
            addCriterion("consumer_ is null");
            return (Criteria) this;
        }

        public Criteria andConsumerIsNotNull() {
            addCriterion("consumer_ is not null");
            return (Criteria) this;
        }

        public Criteria andConsumerEqualTo(Long value) {
            addCriterion("consumer_ =", value, "consumer");
            return (Criteria) this;
        }

        public Criteria andConsumerNotEqualTo(Long value) {
            addCriterion("consumer_ <>", value, "consumer");
            return (Criteria) this;
        }

        public Criteria andConsumerGreaterThan(Long value) {
            addCriterion("consumer_ >", value, "consumer");
            return (Criteria) this;
        }

        public Criteria andConsumerGreaterThanOrEqualTo(Long value) {
            addCriterion("consumer_ >=", value, "consumer");
            return (Criteria) this;
        }

        public Criteria andConsumerLessThan(Long value) {
            addCriterion("consumer_ <", value, "consumer");
            return (Criteria) this;
        }

        public Criteria andConsumerLessThanOrEqualTo(Long value) {
            addCriterion("consumer_ <=", value, "consumer");
            return (Criteria) this;
        }

        public Criteria andConsumerIn(List<Long> values) {
            addCriterion("consumer_ in", values, "consumer");
            return (Criteria) this;
        }

        public Criteria andConsumerNotIn(List<Long> values) {
            addCriterion("consumer_ not in", values, "consumer");
            return (Criteria) this;
        }

        public Criteria andConsumerBetween(Long value1, Long value2) {
            addCriterion("consumer_ between", value1, value2, "consumer");
            return (Criteria) this;
        }

        public Criteria andConsumerNotBetween(Long value1, Long value2) {
            addCriterion("consumer_ not between", value1, value2, "consumer");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNull() {
            addCriterion("operator_ is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("operator_ is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(Long value) {
            addCriterion("operator_ =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(Long value) {
            addCriterion("operator_ <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(Long value) {
            addCriterion("operator_ >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(Long value) {
            addCriterion("operator_ >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(Long value) {
            addCriterion("operator_ <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(Long value) {
            addCriterion("operator_ <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<Long> values) {
            addCriterion("operator_ in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<Long> values) {
            addCriterion("operator_ not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(Long value1, Long value2) {
            addCriterion("operator_ between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(Long value1, Long value2) {
            addCriterion("operator_ not between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andCallTimeIsNull() {
            addCriterion("call_time is null");
            return (Criteria) this;
        }

        public Criteria andCallTimeIsNotNull() {
            addCriterion("call_time is not null");
            return (Criteria) this;
        }

        public Criteria andCallTimeEqualTo(Date value) {
            addCriterion("call_time =", value, "callTime");
            return (Criteria) this;
        }

        public Criteria andCallTimeNotEqualTo(Date value) {
            addCriterion("call_time <>", value, "callTime");
            return (Criteria) this;
        }

        public Criteria andCallTimeGreaterThan(Date value) {
            addCriterion("call_time >", value, "callTime");
            return (Criteria) this;
        }

        public Criteria andCallTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("call_time >=", value, "callTime");
            return (Criteria) this;
        }

        public Criteria andCallTimeLessThan(Date value) {
            addCriterion("call_time <", value, "callTime");
            return (Criteria) this;
        }

        public Criteria andCallTimeLessThanOrEqualTo(Date value) {
            addCriterion("call_time <=", value, "callTime");
            return (Criteria) this;
        }

        public Criteria andCallTimeIn(List<Date> values) {
            addCriterion("call_time in", values, "callTime");
            return (Criteria) this;
        }

        public Criteria andCallTimeNotIn(List<Date> values) {
            addCriterion("call_time not in", values, "callTime");
            return (Criteria) this;
        }

        public Criteria andCallTimeBetween(Date value1, Date value2) {
            addCriterion("call_time between", value1, value2, "callTime");
            return (Criteria) this;
        }

        public Criteria andCallTimeNotBetween(Date value1, Date value2) {
            addCriterion("call_time not between", value1, value2, "callTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeIsNull() {
            addCriterion ("save_time is null");
            return (Criteria) this;
        }

        public Criteria andSaveTimeIsNotNull() {
            addCriterion ("save_time is not null");
            return (Criteria) this;
        }

        public Criteria andSaveTimeEqualTo(Date value) {
            addCriterion ("save_time =", value, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeNotEqualTo(Date value) {
            addCriterion ("save_time <>", value, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeGreaterThan(Date value) {
            addCriterion ("save_time >", value, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeGreaterThanOrEqualTo(Date value) {
            addCriterion ("save_time >=", value, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeLessThan(Date value) {
            addCriterion ("save_time <", value, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeLessThanOrEqualTo(Date value) {
            addCriterion ("save_time <=", value, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeIn(List<Date> values) {
            addCriterion ("save_time in", values, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeNotIn(List<Date> values) {
            addCriterion ("save_time not in", values, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeBetween(Date value1, Date value2) {
            addCriterion ("save_time between", value1, value2, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeNotBetween(Date value1, Date value2) {
            addCriterion ("save_time not between", value1, value2, "saveTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table biz_phone_call
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table biz_phone_call
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

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
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
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}