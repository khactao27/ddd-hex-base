package tech.ibrave.metabucket.infra.peristence.jpa.constant;

/**
 * Author: hungnm
 * Date: 26/05/2023
 */
public class TableConstants {
    public static final String TBL_USER = "tbl_user";
    public static final String TBL_USER_GROUP_MAPPING = "tbl_user_group_mapping";
    public static final String TBL_USER_ROLE_MAPPING = "tbl_user_role_mapping";

    public static class Default {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String ENABLE = "enable";
        public static final String STATUS = "status";
        public static final String USER_ID = "user_id";
        public static final String GROUP_ID = "user_group_id";
        public static final String ROLE_ID = "role_id";
        public static final String PERMISSION = "permissions";
        public static final String VERSION = "version";
        public static final String VALUE = "value";
        public static final String VIRTUAL_FOLDER_ID = "virtual_folder_id";
        public static final String CREATED_DATE = "created_date";
        public static final String UPDATED_DATE = "updated_date";
        public static final String CREATED_BY = "created_by";
        public static final String UPDATED_BY = "updated_by";
        public static final String OWNER = "owner";


        private Default() {
        }
    }

    public static class TblUser {

        public static final String USERNAME = "user_name";
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String PASSWORD = "password";
        public static final String EMAIL = "email";
        public static final String SOURCE = "source";
        public static final String TITLE = "title";
        public static final String PHONE_NUMBER = "phone_number";
        private TblUser() {
        }
    }
}
