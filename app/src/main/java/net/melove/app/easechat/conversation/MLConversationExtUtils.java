package net.melove.app.easechat.conversation;


import android.text.TextUtils;

import com.hyphenate.chat.EMConversation;

import net.melove.app.easechat.application.MLConstants;
import net.melove.app.easechat.communal.util.MLDate;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lzan13 on 2015/12/14 15:27.
 * 会话扩展处理类，用来处理会话对象的扩展信息，
 * 包括：
 * TODO 群组@，
 * 会话置顶，
 * 会话最后操作时间，
 * 会话草稿，
 */
public class MLConversationExtUtils {

    /**
     * 会话实体类构造函数，根据传入的会话对象去
     *
     * @param conversation
     */
    public MLConversationExtUtils(EMConversation conversation) {

    }

    /**
     * 设置会话置顶状态
     *
     * @param conversation 要置顶的会话对象
     * @param top          设置会话是否置顶
     */
    public static void setConversationTop(EMConversation conversation, boolean top) {
        // 获取当前会话对象的扩展
        String ext = conversation.getExtField();
        JSONObject jsonObject = null;
        try {
            // 判断扩展内容是否为空
            if (TextUtils.isEmpty(ext)) {
                jsonObject = new JSONObject();
            } else {
                jsonObject = new JSONObject(ext);
            }
            // 将扩展信息设置给外层的 JSONObject 对象
            jsonObject.put(MLConstants.ML_ATTR_TOP, top);
            // 将扩展信息保存到 Conversation 对象的扩展中去
            conversation.setExtField(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取当前会话是否置顶
     *
     * @param conversation 需要操作的会话对象
     * @return 返回当前会话是否置顶
     */
    public static boolean getConversationTop(EMConversation conversation) {
        // 获取当前会话对象的扩展
        String ext = conversation.getExtField();
        // 判断扩展内容是否为空
        if (TextUtils.isEmpty(ext)) {
            return false;
        }
        try {
            // 根据扩展获取Json对象，然后获取置顶的属性，
            JSONObject jsonObject = new JSONObject(ext);
            return jsonObject.optBoolean(MLConstants.ML_ATTR_TOP);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 设置会话的最后时间
     *
     * @param conversation 要设置的会话对象
     */
    public static void setConversationLastTime(EMConversation conversation) {
        // 获取当前会话对象的扩展
        String ext = conversation.getExtField();
        JSONObject jsonObject = null;
        try {
            // 判断扩展内容是否为空
            if (TextUtils.isEmpty(ext)) {
                jsonObject = new JSONObject();
            } else {
                jsonObject = new JSONObject(ext);
            }
            // 根据当前会话消息数是否为零来设置会话的最后时间
            if (conversation.getAllMessages().size() == 0) {
                jsonObject.put(MLConstants.ML_ATTR_LAST_TIME, MLDate.getCurrentMillisecond());
            } else {
                jsonObject.put(MLConstants.ML_ATTR_LAST_TIME, conversation.getLastMessage().getMsgTime());
            }
            // 将扩展信息保存到 Conversation 对象的扩展中去
            conversation.setExtField(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取会话的最后时间
     *
     * @param conversation 需要获取的会话对象
     * @return 返回此会话最后的时间
     */
    public static long getConversationLastTime(EMConversation conversation) {
        // 获取当前会话对象的扩展
        String ext = conversation.getExtField();
        JSONObject jsonObject = null;
        try {
            // 判断扩展内容是否为空
            if (TextUtils.isEmpty(ext)) {
                jsonObject = new JSONObject();
            } else {
                jsonObject = new JSONObject(ext);
            }
            // 根据扩展的key获取扩展的值
            return jsonObject.optLong(MLConstants.ML_ATTR_LAST_TIME);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return MLDate.getCurrentMillisecond();
    }

    /**
     * 设置当前会话草稿
     *
     * @param conversation 需要设置的会话对象
     * @param draft        需要设置的草稿内容
     */
    public static void setConversationDraft(EMConversation conversation, String draft) {
        // 获取当前会话对象的扩展
        String ext = conversation.getExtField();
        JSONObject jsonObject = null;
        try {
            // 判断扩展内容是否为空
            if (TextUtils.isEmpty(ext)) {
                jsonObject = new JSONObject();
            } else {
                jsonObject = new JSONObject(ext);
            }
            // 将扩展信息设置给 JSONObject 对象
            jsonObject.put(MLConstants.ML_ATTR_DRAFT, draft);
            // 将扩展信息保存到 EMConversation 对象扩展中去
            conversation.setExtField(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取当前会话的草稿内容
     *
     * @param conversation 当前会话
     * @return 返回草稿内容
     */
    public static String getConversationDraft(EMConversation conversation) {
        // 获取当前会话对象的扩展
        String ext = conversation.getExtField();
        JSONObject jsonObject = null;
        try {
            // 判断扩展内容是否为空
            if (TextUtils.isEmpty(ext)) {
                jsonObject = new JSONObject();
            } else {
                jsonObject = new JSONObject(ext);
            }
            // 根据扩展的key获取扩展的值
            return jsonObject.optString(MLConstants.ML_ATTR_DRAFT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }
}