package com.zjh.db;

/**
 * Created by ZhangJinghao on 2018/8/15.
 */



/**
 * ContactInjfoDao  数据库操作类  dao后缀的都是数据库操作类
 * <p>
 * 我们这里的每一个 增删改查 的方法都通过getWritableDatabase()去实例化了一个数据库,这里必须这么做
 * 不客气抽取 成为一个成员变量, 否则报错,若是觉得麻烦可以通过定义方法来置为null和重新赋值
 * <p>
 * —— 其实dao类在这里做得事情特别简单：
 * 1、定义一个构造方法，利用这个方法去实例化一个  数据库帮助类
 * 2、编写dao类的对应的 增删改查 方法。
 */
public class DBDao {

    private MyDBHelper mMyDBHelper;
    public static final String TABLE_PLAYER = "PlayerBean";

    /**
     * dao类需要实例化数据库Help类,只有得到帮助类的对象我们才可以实例化 SQLiteDatabase
     *
     * @param context
     */
    public DBDao(Context context) {
        mMyDBHelper = new MyDBHelper(context);
    }

    public long insertDate(PlayerBean playerBean) {
        // 增删改查每一个方法都要得到数据库，然后操作完成后一定要关闭
        // getWritableDatabase(); 执行后数据库文件才会生成
        SQLiteDatabase sqLiteDatabase = mMyDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PlayerBean.NAME, playerBean.name);
        contentValues.put(PlayerBean.AGE, playerBean.age);
        contentValues.put(PlayerBean.ISMALE, playerBean.isMale);

        long rowid = sqLiteDatabase.insert(TABLE_PLAYER, null, contentValues);

        sqLiteDatabase.close();
        return rowid;
    }

    /**
     * 删除的方法
     */
    public int deleteDate(String name) {
        SQLiteDatabase sqLiteDatabase = mMyDBHelper.getWritableDatabase();
        int deleteResult = sqLiteDatabase.delete(TABLE_PLAYER, PlayerBean.NAME + "=?", new String[]{name});
        sqLiteDatabase.close();
        return deleteResult;
    }

    /**
     * 修改的方法
     */
    public int updateData(String name, String newAge) {
        SQLiteDatabase sqLiteDatabase = mMyDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PlayerBean.AGE, newAge);
        int updateResult = sqLiteDatabase.update(TABLE_PLAYER, contentValues, PlayerBean.NAME + "=?", new String[]{name});
        sqLiteDatabase.close();
        return updateResult;
    }

    /**
     * 查询的方法
     */
    public PlayerBean[] selectData(String name) {

        SQLiteDatabase readableDatabase = mMyDBHelper.getReadableDatabase();
        // 查询比较特别,涉及到 cursor
        Cursor cursor = readableDatabase.rawQuery("" +
                "SELECT *\n" +
                "FROM   [player]\n" +
                "WHERE  [name] = ?;", new String[]{name});

        PlayerBean[] playerInfos = new PlayerBean[cursor.getCount()];
        if (cursor.moveToNext()) {

            int _id = cursor.getInt(cursor.getColumnIndex(PlayerBean.ID));
            String _name = cursor.getString(cursor.getColumnIndex(PlayerBean.NAME));
            int _age = cursor.getInt(cursor.getColumnIndex(PlayerBean.AGE));
            boolean _isMale = Boolean.valueOf(cursor.getString(cursor.getColumnIndex(PlayerBean.ISMALE)));

            playerInfos[cursor.getPosition()] = new PlayerBean(_id, _name, _age, _isMale);
        }
        cursor.close(); // 记得关闭 corsor
        readableDatabase.close(); // 关闭数据库
        return playerInfos;
    }
}