package com.mobileia.mcfacebook.model;

import org.json.JSONObject;

/**
 * Created by matiascamiletti on 9/7/15.
 */
public class MCUserFacebook {

    public String id;
    public String token;

    public String fullname;
    public String email;

    public void fill(JSONObject data) {
        try {
            this.fullname = data.getString("name");

            /*String birthday = "";
            if (jsonObject.has("birthday")) {
                birthday = jsonObject.getString("birthday");
            }
            String gender = "";
            if (jsonObject.has("gender")) {
                gender = jsonObject.getString("gender");
            }
            String city = "";
            if (jsonObject.has("hometown")) {
                city = jsonObject.getJSONObject("hometown").getString("name");
            }*/

            if (data.has("email")) {
                email = data.getString("email");
            }
            //String fbId = jsonObject.getString("id");

        } catch (Exception e) {

        }
    }
}
