package cn.ac.sec.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BizMessageProfessionExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table biz_message_profession
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table biz_message_profession
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table biz_message_profession
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_message_profession
     *
     * @mbggenerated
     */
    public BizMessageProfessionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_message_profession
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_message_profession
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_message_profession
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_message_profession
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_message_profession
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_message_profession
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_message_profession
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
     * This method corresponds to the database table biz_message_profession
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
     * This method corresponds to the database table biz_message_profession
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_message_profession
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
     * This class corresponds to the database table biz_message_profession
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
            addCriterion("id_ is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id_ is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id_ =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id_ <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id_ >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id_ >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id_ <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id_ <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id_ in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id_ not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id_ between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id_ not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAlarmIdIsNull() {
            addCriterion("alarm_id is null");
            return (Criteria) this;
        }

        public Criteria andAlarmIdIsNotNull() {
            addCriterion("alarm_id is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmIdEqualTo(Long value) {
            addCriterion("alarm_id =", value, "alarmId");
            return (Criteria) this;
        }

        public Criteria andAlarmIdNotEqualTo(Long value) {
            addCriterion("alarm_id <>", value, "alarmId");
            return (Criteria) this;
        }

        public Criteria andAlarmIdGreaterThan(Long value) {
            addCriterion("alarm_id >", value, "alarmId");
            return (Criteria) this;
        }

        public Criteria andAlarmIdGreaterThanOrEqualTo(Long value) {
            addCriterion("alarm_id >=", value, "alarmId");
            return (Criteria) this;
        }

        public Criteria andAlarmIdLessThan(Long value) {
            addCriterion("alarm_id <", value, "alarmId");
            return (Criteria) this;
        }

        public Criteria andAlarmIdLessThanOrEqualTo(Long value) {
            addCriterion("alarm_id <=", value, "alarmId");
            return (Criteria) this;
        }

        public Criteria andAlarmIdIn(List<Long> values) {
            addCriterion("alarm_id in", values, "alarmId");
            return (Criteria) this;
        }

        public Criteria andAlarmIdNotIn(List<Long> values) {
            addCriterion("alarm_id not in", values, "alarmId");
            return (Criteria) this;
        }

        public Criteria andAlarmIdBetween(Long value1, Long value2) {
            addCriterion("alarm_id between", value1, value2, "alarmId");
            return (Criteria) this;
        }

        public Criteria andAlarmIdNotBetween(Long value1, Long value2) {
            addCriterion("alarm_id not between", value1, value2, "alarmId");
            return (Criteria) this;
        }

        public Criteria andProcessUserIsNull() {
            addCriterion("process_user is null");
            return (Criteria) this;
        }

        public Criteria andProcessUserIsNotNull() {
            addCriterion("process_user is not null");
            return (Criteria) this;
        }

        public Criteria andProcessUserEqualTo(Long value) {
            addCriterion("process_user =", value, "processUser");
            return (Criteria) this;
        }

        public Criteria andProcessUserNotEqualTo(Long value) {
            addCriterion("process_user <>", value, "processUser");
            return (Criteria) this;
        }

        public Criteria andProcessUserGreaterThan(Long value) {
            addCriterion("process_user >", value, "processUser");
            return (Criteria) this;
        }

        public Criteria andProcessUserGreaterThanOrEqualTo(Long value) {
            addCriterion("process_user >=", value, "processUser");
            return (Criteria) this;
        }

        public Criteria andProcessUserLessThan(Long value) {
            addCriterion("process_user <", value, "processUser");
            return (Criteria) this;
        }

        public Criteria andProcessUserLessThanOrEqualTo(Long value) {
            addCriterion("process_user <=", value, "processUser");
            return (Criteria) this;
        }

        public Criteria andProcessUserIn(List<Long> values) {
            addCriterion("process_user in", values, "processUser");
            return (Criteria) this;
        }

        public Criteria andProcessUserNotIn(List<Long> values) {
            addCriterion("process_user not in", values, "processUser");
            return (Criteria) this;
        }

        public Criteria andProcessUserBetween(Long value1, Long value2) {
            addCriterion("process_user between", value1, value2, "processUser");
            return (Criteria) this;
        }

        public Criteria andProcessUserNotBetween(Long value1, Long value2) {
            addCriterion("process_user not between", value1, value2, "processUser");
            return (Criteria) this;
        }

        public Criteria andProcessContentIsNull() {
            addCriterion("process_content is null");
            return (Criteria) this;
        }

        public Criteria andProcessContentIsNotNull() {
            addCriterion("process_content is not null");
            return (Criteria) this;
        }

        public Criteria andProcessContentEqualTo(String value) {
            addCriterion("process_content =", value, "processContent");
            return (Criteria) this;
        }

        public Criteria andProcessContentNotEqualTo(String value) {
            addCriterion("process_content <>", value, "processContent");
            return (Criteria) this;
        }

        public Criteria andProcessContentGreaterThan(String value) {
            addCriterion("process_content >", value, "processContent");
            return (Criteria) this;
        }

        public Criteria andProcessContentGreaterThanOrEqualTo(String value) {
            addCriterion("process_content >=", value, "processContent");
            return (Criteria) this;
        }

        public Criteria andProcessContentLessThan(String value) {
            addCriterion("process_content <", value, "processContent");
            return (Criteria) this;
        }

        public Criteria andProcessContentLessThanOrEqualTo(String value) {
            addCriterion("process_content <=", value, "processContent");
            return (Criteria) this;
        }

        public Criteria andProcessContentLike(String value) {
            addCriterion("process_content like", value, "processContent");
            return (Criteria) this;
        }

        public Criteria andProcessContentNotLike(String value) {
            addCriterion("process_content not like", value, "processContent");
            return (Criteria) this;
        }

        public Criteria andProcessContentIn(List<String> values) {
            addCriterion("process_content in", values, "processContent");
            return (Criteria) this;
        }

        public Criteria andProcessContentNotIn(List<String> values) {
            addCriterion("process_content not in", values, "processContent");
            return (Criteria) this;
        }

        public Criteria andProcessContentBetween(String value1, String value2) {
            addCriterion("process_content between", value1, value2, "processContent");
            return (Criteria) this;
        }

        public Criteria andProcessContentNotBetween(String value1, String value2) {
            addCriterion("process_content not between", value1, value2, "processContent");
            return (Criteria) this;
        }

        public Criteria andProcessDateIsNull() {
            addCriterion("process_date is null");
            return (Criteria) this;
        }

        public Criteria andProcessDateIsNotNull() {
            addCriterion("process_date is not null");
            return (Criteria) this;
        }

        public Criteria andProcessDateEqualTo(Date value) {
            addCriterion("process_date =", value, "processDate");
            return (Criteria) this;
        }

        public Criteria andProcessDateNotEqualTo(Date value) {
            addCriterion("process_date <>", value, "processDate");
            return (Criteria) this;
        }

        public Criteria andProcessDateGreaterThan(Date value) {
            addCriterion("process_date >", value, "processDate");
            return (Criteria) this;
        }

        public Criteria andProcessDateGreaterThanOrEqualTo(Date value) {
            addCriterion("process_date >=", value, "processDate");
            return (Criteria) this;
        }

        public Criteria andProcessDateLessThan(Date value) {
            addCriterion("process_date <", value, "processDate");
            return (Criteria) this;
        }

        public Criteria andProcessDateLessThanOrEqualTo(Date value) {
            addCriterion("process_date <=", value, "processDate");
            return (Criteria) this;
        }

        public Criteria andProcessDateIn(List<Date> values) {
            addCriterion("process_date in", values, "processDate");
            return (Criteria) this;
        }

        public Criteria andProcessDateNotIn(List<Date> values) {
            addCriterion("process_date not in", values, "processDate");
            return (Criteria) this;
        }

        public Criteria andProcessDateBetween(Date value1, Date value2) {
            addCriterion("process_date between", value1, value2, "processDate");
            return (Criteria) this;
        }

        public Criteria andProcessDateNotBetween(Date value1, Date value2) {
            addCriterion("process_date not between", value1, value2, "processDate");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status_ is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status_ is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status_ =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status_ <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status_ >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status_ >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status_ <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status_ <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status_ in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status_ not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status_ between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status_ not between", value1, value2, "status");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table biz_message_profession
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
     * This class corresponds to the database table biz_message_profession
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