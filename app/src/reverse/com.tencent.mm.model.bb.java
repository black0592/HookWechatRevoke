package com.tencent.mm.model;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Looper;
import android.util.Base64;
import com.tencent.mm.A;
import com.tencent.mm.BuildConfig;
import com.tencent.mm.a.f;
import com.tencent.mm.compatible.d.p;
import com.tencent.mm.compatible.d.u;
import com.tencent.mm.d.a.ih;
import com.tencent.mm.d.b.ba;
import com.tencent.mm.d.b.s;
import com.tencent.mm.platformtools.n;
import com.tencent.mm.pointers.PByteArray;
import com.tencent.mm.protocal.MMProtocalJni;
import com.tencent.mm.protocal.b.adt;
import com.tencent.mm.protocal.b.adu;
import com.tencent.mm.protocal.b.adv;
import com.tencent.mm.protocal.b.af;
import com.tencent.mm.protocal.b.zg;
import com.tencent.mm.q.c;
import com.tencent.mm.s.ai;
import com.tencent.mm.s.d;
import com.tencent.mm.sdk.platformtools.MapClass;
import com.tencent.mm.sdk.platformtools.ab;
import com.tencent.mm.sdk.platformtools.at;
import com.tencent.mm.sdk.platformtools.bc;
import com.tencent.mm.sdk.platformtools.v;
import com.tencent.mm.sdk.platformtools.z;
import com.tencent.mm.storage.ad;
import com.tencent.mm.storage.h;
import com.tencent.mm.storage.r;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class bb implements c {
    public interface a {
        void aU(String arg1);
    }

    public interface b {
        void a(af arg1);
    }

    private long bvE;
    public a bvF;
    private Map bvG;
    private Map bvH;

    public bb() {
        super();
        this.bvE = -1;
        this.bvG = new HashMap();
        this.bvH = new HashMap();
        if(!BuildConfig.SKIP) {
            A.a();
        }
    }

    private void a(String arg7, af arg8, boolean arg9) {
        Map v0 = arg9 ? this.bvH : this.bvG;
        Object v0_1 = v0.get(arg7);
        if(v0_1 == null || (((List)v0_1).isEmpty())) {
            v.w("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "listener list is empty, return now");
        }
        else {
            v.i("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "listener list size is %d", new
                    Object[]{Integer.valueOf(((List)v0_1).size())});
            Iterator v1 = ((List)v0_1).iterator();
            while(v1.hasNext()) {
                v1.next().a(arg8);
            }
        }
    }

    public final void a(String arg3, b arg4, boolean arg5) {
        if(!bc.jY(arg3) && arg4 != null) {
            Map v1 = arg5 ? this.bvH : this.bvG;
            Object v0 = v1.get(arg3);
            if(v0 == null) {
                LinkedList v0_1 = new LinkedList();
                v1.put(arg3, v0_1);
            }

            if(((List)v0).contains(arg4)) {
                return;
            }

            ((List)v0).add(arg4);
        }
    }

    public final com.tencent.mm.q.c$a b(af arg19) {
        h v5_9;
        boolean v2_11;
        com.tencent.mm.storage.j$a v6_7;
        byte[] v6_5;
        String v4_4;
        String v2_7;
        Object v8;
        String v3_4;
        com.tencent.mm.q.c$a v4_3;
        int v7_3;
        String v5_3;
        ih v7_2;
        r v7_1;
        long v5_1;
        int v3_2;
        boolean v4_1;
        Object v4;
        Object v3_1;
        Object v2_4;
        c v2_3;
        Object v10_1;
        Map v3;
        int v2_2;
        String v10;
        Map v9;
        switch(arg19.ikw) {
            case 10001: {
                goto label_15;
            }
            case 10002: {
                goto label_33;
            }
        }

        v.w("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "cmdAM msgType is %d, ignore, return now",
                new Object[]{Integer.valueOf(arg19.ikw)});
        com.tencent.mm.q.c$a v2 = null;
        return v2;
        label_33:
        String v7 = n.a(arg19.ikx);
        if(bc.jY(v7)) {
            v.e("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "null msg content");
            return null;
        }

        if(v7.startsWith("~SEMI_XML~")) {
            Map v2_1 = at.AR(v7);
            if(v2_1 == null) {
                v.e("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "SemiXml values is null, msgContent %s",
                        new Object[]{v7});
                return null;
            }
            else {
                v9 = v2_1;
                v10 = "brand_service";
            }
        }
        else {
            v2_2 = v7.indexOf("<sysmsg");
            if(v2_2 == -1) {
                v.e("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "msgContent not start with <sysmsg");
                return null;
            }
            else {
                v3 = MapClass.hookMap(v7.substring(v2_2), "sysmsg", null);
                if(v3 == null) {
                    v.e("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "KVConfig values is null, msgContent %s",
                            new Object[]{v7});
                    return null;
                }
                else {
                    v9 = v3;
                    v10_1 = v3.get(".sysmsg.$type");
                }
            }
        }

        v.d("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "recieve a syscmd_newxml %s subType %s",
                new Object[]{v7, ((String)v10_1)});
        if((((String)v10_1)) != null) {
            this.a(((String)v10_1), arg19, true);
        }

        com.tencent.mm.q.c$a v5 = null;
        if((((String)v10_1)) != null && (((String)v10_1).equals("addcontact"))) {
            arg19.ikx = n.jV(v9.get(".sysmsg.addcontact.content"));
            v2_3 = com.tencent.mm.q.c$b.X(Integer.valueOf(1));
            v5 = v2_3 == null ? null : v2_3.b(arg19);
        }

        if((((String)v10_1)) != null && (((String)v10_1).equals("dynacfg"))) {
            com.tencent.mm.g.h.pS().a(v7, v9, false);
            com.tencent.mm.g.h.pT();
            if(com.tencent.mm.g.c.pA() == 2) {
                com.tencent.mm.plugin.report.service.h.fxL.E(10879, "");
            }

            v.d("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "Mute_Room_Disable:" + Integer.toString(
                    bc.getInt(com.tencent.mm.g.h.pS().getValue("MuteRoomDisable"), 0)));
        }

        if((((String)v10_1)) != null && (((String)v10_1).equals("dynacfg_split"))) {
            com.tencent.mm.g.h.pS().a(v7, v9, true);
        }

        if((((String)v10_1)) != null && (((String)v10_1).equals("banner"))) {
            v2_4 = v9.get(".sysmsg.mainframebanner.$type");
            v3_1 = v9.get(".sysmsg.mainframebanner.showtype");
            v4 = v9.get(".sysmsg.mainframebanner.data");
            if(v2_4 != null && ((String)v2_4).length() > 0) {
                try {
                    ao.tM().a(new an(Integer.valueOf(((String)v2_4)).intValue(), Integer.valueOf(((String)
                            v3_1)).intValue(), ((String)v4)));
                }
                catch(Exception v2_5) {
                    v.e("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", v2_5.toString());
                }
            }

            v2_4 = v9.get(".sysmsg.friendrecommand.fromuser");
            v3_1 = v9.get(".sysmsg.friendrecommand.touser");
            if(v2_4 != null && v3_1 != null) {
                try {
                    ah.tn().rL().a(((String)v3_1), true, null);
                }
                catch(Exception v2_5) {
                    v.e("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", v2_5.toString());
                }
            }

            v2_4 = v9.get(".sysmsg.banner.securitybanner.chatname");
            v3_1 = v9.get(".sysmsg.banner.securitybanner.wording");
            v4 = v9.get(".sysmsg.banner.securitybanner.showtype");
            if(bc.jY(((String)v2_4))) {
                goto label_178;
            }

            if(bc.jY(((String)v4))) {
                goto label_178;
            }

            try {
                if(((String)v4).equals("1")) {
                    v4_1 = true;
                }
                else {
                    goto label_1385;
                }

                goto label_171;
            }
            catch(Exception v2_5) {
                goto label_336;
            }

            label_1385:
            v4_1 = false;
            try {
                label_171:
                ah.tn().rM().a(((String)v2_4), v4_1, new String[]{v3_1});
            }
            catch(Exception v2_5) {
                label_336:
                v.e("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "[oneliang]" + v2_5.toString());
            }
        }

        label_178:
        if(!bc.jY(((String)v10_1)) && (((String)v10_1).equals("midinfo"))) {
            v2_4 = v9.get(".sysmsg.midinfo.json_buffer");
            v3_1 = v9.get(".sysmsg.midinfo.time_interval");
            v.i("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "QueryMid time[%s] json[%s]  [%s] ",
                    new Object[]{v3_1, v2_4, v7});
            v3_2 = bc.getInt(((String)v3_1), 0);
            if((((long)v3_2)) > 86400 && (((long)v3_2)) < 864000) {
                ah.tn().rf().set(331777, Long.valueOf(bc.EY() + (((long)v3_2))));
            }

            if(bc.jY(((String)v2_4))) {
                goto label_217;
            }

            com.tencent.mm.plugin.report.b.c.rO(((String)v2_4));
        }

        label_217:
        if((((String)v10_1)) != null && (((String)v10_1).equals("revokemsg"))) {
            v.i("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "mm hit MM_DATA_SYSCMD_NEWXML_SUBTYPE_REVOKE");
            v2_4 = v9.get(".sysmsg.revokemsg.session");
            v3_1 = v9.get(".sysmsg.revokemsg.newmsgid");
            v4 = v9.get(".sysmsg.revokemsg.replacemsg");
            v.i("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "ashutest::[oneliang][xml parse] ,msgId:%s,replaceMsg:%s ",
                    new Object[]{v3_1, v4});
            try {
                v5_1 = Long.parseLong(((String)v3_1));
                ad v2_6 = ah.tn().rk().r(((String)v2_4), v5_1);
                ad v3_3 = ad.B(v2_6);
                v2_6.setContent(((String)v4));
                v2_6.setType(10000);
                ah.tn().rk().a(((ba)v2_6).field_msgId, v2_6);
                v7_1 = ah.tn().rl().Cs(((ba)v2_6).field_talker);
                if(v7_1 != null && ((s)v7_1).field_unReadCount > 0 && ((s)v7_1).field_unReadCount >=
                        ah.tn().rk().C(v2_6)) {
                    v7_1.bi(((s)v7_1).field_unReadCount - 1);
                    ah.tn().rl().a(v7_1, ((s)v7_1).field_username, true);
                }

                v7_2 = new ih();
                v7_2.aDr.avx = ((ba)v2_6).field_msgId;
                v7_2.aDr.aDs = ((String)v4);
                v7_2.aDr.aCM = v2_6;
                v7_2.aDr.aDt = v3_3;
                com.tencent.mm.sdk.c.a.jeN.g(((com.tencent.mm.sdk.c.b)v7_2));
            }
            catch(Exception v2_5) {
                v.printErrStackTrace("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", ((Throwable)
                        v2_5), "[oneliang][revokeMsg] msgId:%d,error:%s", new Object[]{Long.valueOf(
                        v5_1), v2_5.toString()});
            }

            return null;
        }

        if((((String)v10_1)) != null && (((String)v10_1).equals("clouddelmsg"))) {
            v.i("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "mm hit MM_DATA_SYSCMD_NEWXML_CLOUD_DEL_MSG");
            v2_4 = v9.get(".sysmsg.clouddelmsg.delcommand");
            v3_1 = v9.get(".sysmsg.clouddelmsg.msgid");
            v4 = v9.get(".sysmsg.clouddelmsg.fromuser");
            int v5_2 = v7.indexOf("<msg>");
            int v6 = v7.indexOf("</msg>");
            v5_3 = v5_2 == -1 || v6 == -1 ? "" : at.K(MapClass.hookMap(v7.substring(v5_2, v6 + 6), "msg",
                    null));
            v.i("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "[hakon][clouddelmsg], delcommand:%s, msgid:%s, fromuser:%s, sysmsgcontent:%s",
                    new Object[]{v2_4, v3_1, v4, v5_3});
            try {
                LinkedList v4_2 = ah.tn().rk().cd(((String)v4), ((String)v3_1));
                if(v4_2 != null && v4_2.size() > 0) {
                    Iterator v6_1 = v4_2.iterator();
                    while(true) {
                        if(v6_1.hasNext()) {
                            v4 = v6_1.next();
                            if(v4 == null) {
                                v.e("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "[hakon][clouddelmsg], msgInfo == null");
                            }
                            else if(((ba)v4).field_msgSvrId < 0) {
                                v.e("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "[hakon][clouddelmsg], invalid msgInfo.msgId = %s, srvId = %s",
                                        new Object[]{Long.valueOf(((ba)v4).field_msgId), Long.valueOf(((
                                                ba)v4).field_msgSvrId)});
                            }
                            else {
                                v.i("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "[hakon][clouddelmsg], msgInfo.msgId = %s, srvId = %s",
                                        new Object[]{Long.valueOf(((ba)v4).field_msgId), Long.valueOf(((
                                                ba)v4).field_msgSvrId)});
                                v7_3 = Integer.parseInt(((String)v2_4));
                                if(v7_3 == 1) {
                                    ah.tn().rk().u(((ba)v4).field_talker, ((ba)v4).field_msgSvrId);
                                }
                                else if(v7_3 == 2 && ((ba)v4).field_type == 285212721) {
                                    ((ad)v4).setContent(v5_3);
                                    ah.tn().rk().b(((ba)v4).field_msgSvrId, ((ad)v4));
                                    v7_1 = ah.tn().rl().Cs(((ba)v4).field_talker);
                                    if(v7_1 != null && ((s)v7_1).field_unReadCount > 0 && ((s)v7_1).
                                            field_unReadCount >= ah.tn().rk().C(((ad)v4))) {
                                        v7_1.bi(((s)v7_1).field_unReadCount - 1);
                                        ah.tn().rl().a(v7_1, ((s)v7_1).field_username, true);
                                    }
                                }

                                v7_2 = new ih();
                                v7_2.aDr.avx = ((ba)v4).field_msgId;
                                v7_2.aDr.aDs = v5_3;
                                v7_2.aDr.aCM = ((ad)v4);
                                com.tencent.mm.sdk.c.a.jeN.g(((com.tencent.mm.sdk.c.b)v7_2));
                            }

                            continue;
                        }
                        else {
                            return null;
                        }
                    }
                }

                v.e("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "get null by getByBizClientMsgId");
                return null;
            }
            catch(Exception v2_5) {
                v.printErrStackTrace("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", ((Throwable)
                        v2_5), "[hakon][clouddelmsg], BizClientMsgId:%d,error:%s", new Object[]{v3_1,
                        v2_5.toString()});
            }

            return null;
        }

        if((((String)v10_1)) == null || !((String)v10_1).equals("updatepackage")) {
            v4_3 = v5;
        }
        else {
            v2_3 = com.tencent.mm.q.c$b.X(Integer.valueOf(-1879048175));
            v4_3 = v2_3 == null ? null : v2_3.b(arg19);
        }

        if((((String)v10_1)) != null && (((String)v10_1).equals("deletepackage"))) {
            v2_3 = com.tencent.mm.q.c$b.X(Integer.valueOf(-1879048174));
            v4_3 = v2_3 == null ? null : v2_3.b(arg19);
        }

        if((((String)v10_1)) != null && (((String)v10_1).equals("abtest"))) {
            v2_3 = com.tencent.mm.q.c$b.X(Integer.valueOf(-1879048184));
            v4_3 = v2_3 == null ? null : v2_3.b(arg19);
        }

        if((((String)v10_1)) != null && (((String)v10_1).equals("delchatroommember"))) {
            v3_4 = n.a(arg19.iku);
            ad v5_4 = ah.tn().rk().r(v3_4, arg19.ikC);
            v2_2 = 0;
            if(((ba)v5_4).field_msgId > 0) {
                v2_2 = 1;
            }

            v5_4.t(arg19.ikC);
            v5_4.u(ap.c(v3_4, ((long)arg19.eSX)));
            v5_4.setType(10002);
            v5_4.setContent(v7);
            v5_4.bl(0);
            v5_4.setTalker(v3_4);
            v5_4.co(arg19.ikA);
            if(v2_2 == 0) {
                ap.e(v5_4);
                goto label_571;
            }

            ah.tn().rk().b(arg19.ikC, v5_4);
        }

        label_571:
        if((((String)v10_1)) != null && (((String)v10_1).equals("WakenPush")) && this.bvE != arg19.ikC
                ) {
            this.bvE = arg19.ikC;
            v.i("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "dzmonster[subType wakenpush]");
            bg v13 = new bg(v9);
            Object v5_5 = v13.bvQ.get(".sysmsg.WakenPush.PushContent");
            v3_1 = v13.bvQ.get(".sysmsg.WakenPush.Jump");
            v2_4 = v13.bvQ.get(".sysmsg.WakenPush.ExpiredTime");
            v8 = v13.bvQ.get(".sysmsg.WakenPush.Username");
            v.d("!44@/B4Tb64lLpK8mLK+NvBrvMTRy/81FbtdqpXPs02R2Co=", "dzmonster[xml parse of wakenpush,pushContent:%s, jump:%s, expiredTime %s]",
                    new Object[]{v5_5, v3_1, v2_4});
            v2_7 = com.tencent.mm.g.h.pS().getValue("WakenPushDeepLinkBitSet");
            v.d("!44@/B4Tb64lLpK8mLK+NvBrvMTRy/81FbtdqpXPs02R2Co=", "dzmonster[config of WakenPushDeepLinkBitSet:%s",
                    new Object[]{v2_7});
            long v11 = !bc.jY(v2_7) ? Long.parseLong(v2_7) : 0;
            Bitmap v7_4 = com.tencent.mm.p.b.a(((String)v8), false, -1);
            y v2_8 = ah.lc();
            if(bc.jY(((String)v3_1))) {
                v.e("!44@/B4Tb64lLpK8mLK+NvBrvMTRy/81FbtdqpXPs02R2Co=", "dzmonster:dealDeepLink[url is null]");
                v4_4 = "com.tencent.mm.ui.LauncherUI";
            }
            else {
                if((4 & v11) == 4 && (((String)v3_1).startsWith("weixin://dl/moments"))) {
                    v4_4 = "com.tencent.mm.plugin.sns.ui.SnsTimeLineUI";
                    goto label_637;
                }

                if((v11 & 262144) == 262144 && (((String)v3_1).startsWith("weixin://dl/recommendation"))
                        ) {
                    v4_4 = "com.tencent.mm.plugin.subapp.ui.friend.FMessageConversationUI";
                    goto label_637;
                }

                v.e("!44@/B4Tb64lLpK8mLK+NvBrvMTRy/81FbtdqpXPs02R2Co=", "dzmonster:dealDeepLink[unable to deal with the deep link:%s)",
                        new Object[]{v3_1});
                v4_4 = "com.tencent.mm.ui.LauncherUI";
            }

            label_637:
            Intent v6_2 = new Intent();
            v6_2.setClassName(z.getContext(), v4_4);
            v6_2.setFlags(536870912);
            v6_2.putExtra("LauncherUI.Show.Update.DialogMsg", v13.bvQ.get(".sysmsg.WakenPush.PushContent"));
            if(!((String)v3_1).equals("weixin://dl/update_newest_version")) {
                v6_2.putExtra("LauncherUI.Show.Update.Url", v13.bvQ.get(".sysmsg.WakenPush.Jump"));
            }

            Notification v2_9 = v2_8.a(PendingIntent.getActivity(z.getContext(), UUID.randomUUID().hashCode(),
                    v6_2, 134217728), z.getContext().getString(2131298506), ((String)v5_5), v5_5, v7_4, ((
                    String)v8));
            ((Notification)v2_8).flags |= 16;
            ah.lc().a(v2_9, false);
            v4_3 = null;
        }

        if((((String)v10_1)) != null && (((String)v10_1).equals("DisasterNotice"))) {
            v2_4 = v9.get(".sysmsg.NoticeId");
            v3_1 = v9.get(".sysmsg.Content");
            v.i("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "disaster noticeID:%s, content:%s",
                    new Object[]{v2_4, v3_1});
            SharedPreferences v8_1 = z.getContext().getSharedPreferences("disaster_pref", 4);
            v5_3 = v8_1.getString("disaster_noticeid_list_key", "");
            if(!v5_3.contains(((CharSequence)v2_4))) {
                String[] v11_1 = v5_3.split(";");
                if(v11_1 != null && v11_1.length > 10) {
                    int v12 = v11_1.length;
                    v7_3 = 0;
                    v4_4 = "";
                    while(true) {
                        if(v7_3 < v12) {
                            String[] v6_3 = v11_1[v7_3].split(",");
                            try {
                                if(bc.ak(Long.parseLong(v6_3[0])) < 1296000) {
                                    v4_4 = v4_4 + v6_3[0] + "," + v6_3[1] + ";";
                                }
                                else {
                                }
                            }
                            catch(Exception v6_4) {
                                v.e("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "MM_DATA_SYSCMD_NEWXML_DISASTER_NOTICE parseLong error:%s",
                                        new Object[]{v6_4});
                            }

                            ++v7_3;
                            continue;
                        }
                        else {
                            goto label_792;
                        }
                    }
                }

                v4_4 = v5_3;
                label_792:
                v4_4 = v4_4 + bc.EY() + "," + (((String)v2_4)) + ";";
                v.i("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "update noticeIdList %s -> %s",
                        new Object[]{v5_3, v4_4});
                v8_1.edit().putString("disaster_noticeid_list_key", v4_4).commit();
            }

            new ab(Looper.getMainLooper()).post(new Runnable() {
                public final void run() {
                    if(this.bvJ.bvF != null) {
                        this.bvJ.bvF.aU(this.bvI);
                    }
                }
            });
            arg19.ikx = n.jV(((String)v3_1));
            arg19.ikw = 1;
            v2_3 = com.tencent.mm.q.c$b.X(Integer.valueOf(1));
            v4_3 = v2_3 == null ? null : v2_3.b(arg19);
        }

        if((((String)v10_1)) != null && (((String)v10_1).equals("EmotionKv"))) {
            v2_4 = v9.get(".sysmsg.EmotionKv.K");
            v8 = v9.get(".sysmsg.EmotionKv.I");
            if(v2_4 == null) {
                v3_4 = "";
            }
            else {
                v3_1 = v2_4;
            }

            v.i("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "summercck emotionkv pcKeyStr len:%d, content[%s] pcId[%s]",
                    new Object[]{Integer.valueOf(((String)v3_1).length()), ((String)v3_1), v8});
            byte[] v4_5 = com.tencent.mm.protocal.z.aMN().iiy.getBytes();
            byte[] v5_6 = com.tencent.mm.protocal.z.aMN().iiz.getBytes();
            try {
                v6_5 = ah.to().byu.vG().vD();
            }
            catch(Exception v2_5) {
                v.e("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "DISASTER_NOTICE :%s ", new
                        Object[]{bc.b(((Throwable)v2_5))});
            }

            if(bc.I(v6_5)) {
                v.e("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "DISASTER_NOTICE  ecdh  is null .");
                return null;
            }

            PByteArray v7_5 = new PByteArray();
            if((bc.jY(((String)v3_1))) || (bc.I(v5_6)) || (bc.I(v4_5)) || (bc.I(v6_5))) {
                String v9_1 = "!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=";
                v10 = "summercck emotionkv param len err pcKeylen:%d, keynlen:%d, keyelen:%d, ecdhlen:%d";
                Object[] v11_2 = new Object[4];
                v2_2 = (((String)v3_1)) == null ? -1 : ((String)v3_1).length();
                v11_2[0] = Integer.valueOf(v2_2);
                v2_2 = v5_6 == null ? -1 : v5_6.length;
                v11_2[1] = Integer.valueOf(v2_2);
                v3_2 = 2;
                v2_2 = v4_5 == null ? -1 : v4_5.length;
                v11_2[v3_2] = Integer.valueOf(v2_2);
                v3_2 = 3;
                v2_2 = v6_5 == null ? -1 : v6_5.length;
                v11_2[v3_2] = Integer.valueOf(v2_2);
                v.w(v9_1, v10, v11_2);
            }
            else {
                MMProtocalJni.genClientCheckKVRes(ah.tn().uin, ((String)v3_1), v4_5, v5_6, v6_5, v7_5);
            }

            byte[] v2_10 = v7_5.value;
            zg v3_5 = new zg();
            v3_5.fxu = v7_5.value != null ? new String(v7_5.value) : "";
            v4_4 = "!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=";
            v5_3 = "summercck emotionkv res len:%d val len:%d, content[%s]";
            Object[] v6_6 = new Object[3];
            v2_2 = v2_10 == null ? -1 : v2_10.length;
            v6_6[0] = Integer.valueOf(v2_2);
            v6_6[1] = Integer.valueOf(v3_5.fxu.length());
            v6_6[2] = f.m(v3_5.fxu.getBytes());
            v.i(v4_4, v5_3, v6_6);
            v3_5.iIP = ((String)v8);
            ah.tn().rh().b(new com.tencent.mm.ae.b$a(59, ((com.tencent.mm.aq.a)v3_5)));
            return null;
        }

        if((((String)v10_1)) != null && (((String)v10_1).equals("qy_status_notify"))) {
            v2_4 = v9.get(".sysmsg.chat_id");
            v9.get(".sysmsg.last_create_time");
            v3_1 = v9.get(".sysmsg.brand_username");
            long v4_6 = com.tencent.mm.s.f.gj(((String)v2_4));
            if(v4_6 == -1) {
                v.i("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "qy_status_notify bizLocalId == -1,%s",
                        new Object[]{v2_4});
                v2 = null;
            }
            else {
                v2_2 = ai.xt().I(v4_6).field_newUnReadCount;
                ai.xt().K(v4_6);
                d v4_7 = ai.xs().O(v4_6);
                r v5_7 = ah.tn().rl().Cs(((String)v3_1));
                if(v5_7 == null) {
                    v.w("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "qy_status_notify cvs == null:%s",
                            new Object[]{v3_1});
                    v2 = null;
                }
                else if(v4_7.cX(1)) {
                    if(((s)v5_7).field_unReadMuteCount <= v2_2) {
                        v5_7.bp(0);
                        ah.tn().rl().a(v5_7, ((String)v3_1), true);
                        ah.lc().cancelNotification(((String)v3_1));
                    }
                    else {
                        v5_7.bp(((s)v5_7).field_unReadMuteCount - v2_2);
                        ah.tn().rl().a(v5_7, ((String)v3_1), true);
                    }

                    v2 = null;
                }
                else {
                    if(((s)v5_7).field_unReadCount <= v2_2) {
                        ah.tn().rl().Cu(((String)v3_1));
                        ah.lc().cancelNotification(((String)v3_1));
                    }
                    else {
                        v5_7.bo(0);
                        v5_7.bi(((s)v5_7).field_unReadCount - v2_2);
                        ah.tn().rl().a(v5_7, ((String)v3_1), true);
                    }

                    v2 = null;
                }
            }

            return v2;
        }

        if((((String)v10_1)) != null && (((String)v10_1).equals("qy_chat_update"))) {
            v2_4 = v9.get(".sysmsg.chat_id");
            v3_1 = v9.get(".sysmsg.ver");
            v9.get(".sysmsg.brand_username");
            d v5_8 = ai.xs().gg(((String)v2_4));
            if(v5_8 == null) {
                v.i("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "qy_status_notify bizChatInfo == null:%s",
                        new Object[]{v2_4});
                return null;
            }
            else if(v5_8.field_chatVersion < bc.getInt(((String)v3_1), 2147483647)) {
                v5_8.field_needToUpdate = true;
                ai.xs().b(v5_8);
            }
        }

        if((((String)v10_1)) != null && (((String)v10_1).equals("bindmobiletip"))) {
            v3_2 = bc.getInt(v9.get(".sysmsg.bindmobiletip.forcebind"), 0);
            v4_4 = bc.jX(v9.get(".sysmsg.bindmobiletip.deviceid"));
            v5_3 = bc.jX(v9.get(".sysmsg.bindmobiletip.wording"));
            v2_7 = new String(Base64.decode(v4_4.getBytes(), 0));
            v4_4 = new String(com.tencent.mm.aq.b.aD(p.oz().getBytes()).og(16).ihc);
            v.i("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "summerbindmobile forceBind:%d,decodeDeviceId[%s],localDeviceId[%s],woridingStr[%s]",
                    new Object[]{Integer.valueOf(v3_2), v2_7, v4_4, v5_3});
            if((bc.jY(v2_7)) || (v2_7.equals(v4_4))) {
                ah.tn().rf().b(com.tencent.mm.storage.j$a.jmN, Boolean.valueOf(true));
                h v4_8 = ah.tn().rf();
                v6_7 = com.tencent.mm.storage.j$a.jmO;
                v2_11 = v3_2 == 1 ? true : false;
                v4_8.b(v6_7, Boolean.valueOf(v2_11));
                ah.tn().rf().b(com.tencent.mm.storage.j$a.jmP, v5_3);
            }

            return null;
        }

        if((((String)v10_1)) != null && (((String)v10_1).equals("ClientCheckConsistency"))) {
            adt v3_6 = new adt();
            v3_6.iOi = v9.get(".sysmsg.ClientCheckConsistency.clientcheck.fullpathfilename");
            v3_6.iOj = bc.getInt(v9.get(".sysmsg.ClientCheckConsistency.clientcheck.fileoffset"), 0);
            v3_6.iOk = bc.getInt(v9.get(".sysmsg.ClientCheckConsistency.clientcheck.checkbuffersize"),
                    0);
            v3_6.iqj = bc.getInt(v9.get(".sysmsg.ClientCheckConsistency.clientcheck.seq"), 0);
            ((adt)v3).iOl = ar.a(((adt)v3).iOi, ((long)((adt)v3).iOj), ((long)((adt)v3).iOk));
            ((adt)v3).iuj = ((int)ar.fi(((adt)v3).iOi));
            v2_2 = ar.checkMsgLevel() ? 1 : 0;
            v3_6.iOm = v2_2;
            v3_6.itm = u.oP();
            v3_6.iOn = ar.c(new Object[]{v3_6.iOi, Integer.valueOf(v3_6.iOj), Integer.valueOf(v3_6.iOk),
                    Integer.valueOf(v3_6.iqj), v3_6.iOl, Integer.valueOf(v3_6.iuj), Integer.valueOf(
                    v3_6.iOm), Integer.valueOf(v3_6.itm)});
            ah.tn().rh().b(new com.tencent.mm.ae.b$a(61, ((com.tencent.mm.aq.a)v3_6)));
        }

        if((((String)v10_1)) != null && (((String)v10_1).equals("ClientCheckHook"))) {
            adv v3_7 = new adv();
            v3_7.iqj = bc.getInt(v9.get(".sysmsg.ClientCheckHook.clientcheck.seq"), 0);
            v3_7.iOp = ar.ud();
            v2_2 = ar.checkMsgLevel() ? 1 : 0;
            v3_7.iOm = v2_2;
            v3_7.itm = u.oP();
            v3_7.iOn = ar.c(new Object[]{Integer.valueOf(v3_7.iqj), v3_7.iOp, Integer.valueOf(v3_7.iOm),
                    Integer.valueOf(v3_7.itm)});
            ah.tn().rh().b(new com.tencent.mm.ae.b$a(62, ((com.tencent.mm.aq.a)v3_7)));
        }

        if((((String)v10_1)) != null && (((String)v10_1).equals("ClientCheckGetAppList"))) {
            adu v3_8 = new adu();
            v3_8.iqj = bc.getInt(v9.get(".sysmsg.ClientCheckGetAppList.clientcheck.seq"), 0);
            v3_8.iOo = ar.ue();
            v2_2 = ar.checkMsgLevel() ? 1 : 0;
            v3_8.iOm = v2_2;
            v3_8.itm = u.oP();
            v3_8.iOn = ar.c(new Object[]{Integer.valueOf(v3_8.iqj), v3_8.iOo, Integer.valueOf(v3_8.iOm),
                    Integer.valueOf(v3_8.itm)});
            ah.tn().rh().b(new com.tencent.mm.ae.b$a(63, ((com.tencent.mm.aq.a)v3_8)));
        }

        if((((String)v10_1)) != null && (((String)v10_1).equals("WeChatOut"))) {
            v3_2 = bc.getInt(v9.get(".sysmsg.RedDot"), 0);
            v2_2 = bc.getInt(v9.get(".sysmsg.scene"), -1);
            v.i("!44@/B4Tb64lLpLqQYyN0INswY/XO4icKaZhr30o48Gx2tE=", "WeChatOut RedDot: %d", new Object
                    []{Integer.valueOf(v3_2)});
            if(v2_2 == 0) {
                v5_9 = ah.tn().rf();
                v6_7 = com.tencent.mm.storage.j$a.jnc;
                v2_11 = v3_2 == 1 ? true : false;
                v5_9.b(v6_7, Boolean.valueOf(v2_11));
            }
            else {
                if(1 != v2_2) {
                    return v4_3;
                }

                v5_9 = ah.tn().rf();
                v6_7 = com.tencent.mm.storage.j$a.jnd;
                v2_11 = v3_2 == 1 ? true : false;
                v5_9.b(v6_7, Boolean.valueOf(v2_11));
            }
        }

        return v4_3;
        label_15:
        v2_7 = n.a(arg19.iku);
        n.a(arg19.ikx);
        this.a(v2_7, arg19, false);
        com.tencent.mm.plugin.report.service.h.fxL.E(10395, String.valueOf(arg19.ikC));
        return null;
    }

    public final void b(String arg2, b arg3, boolean arg4) {
        if(!bc.jY(arg2) && arg3 != null) {
            Map v0 = arg4 ? this.bvH : this.bvG;
            Object v0_1 = v0.get(arg2);
            if(v0_1 == null) {
                return;
            }

            ((List)v0_1).remove(arg3);
        }
    }

    public final void d(ad arg1) {
    }
}

