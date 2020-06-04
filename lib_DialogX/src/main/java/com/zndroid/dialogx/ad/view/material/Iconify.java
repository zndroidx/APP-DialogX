package com.zndroid.dialogx.ad.view.material;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spanned;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import static android.text.Html.fromHtml;
import static android.text.Html.toHtml;
import static java.lang.String.valueOf;

public final class Iconify {

    private static final String TTF_FILE = "material-design-iconic-font-2.1.1.ttf";

    public static final String TAG = Iconify.class.getSimpleName();

    private static Typeface typeface = null;

    private Iconify() {
        // Prevent instantiation
    }

    /**
     * Transform the given TextViews replacing {icon_xxx} texts with icons.
     */
    public static final void addIcons(TextView... textViews) {
        for (TextView textView : textViews) {
            textView.setTypeface(getTypeface(textView.getContext()));
            textView.setText(compute(textView.getText()));
        }
    }

    public static void addIconsEditMode(TextView... textViews) {
        for (TextView textView : textViews) {
            textView.setTypeface(getTypefaceEditMode());
            textView.setText(compute(textView.getText()));
        }
    }

    public static CharSequence compute(CharSequence charSequence) {
        if (charSequence instanceof Spanned) {
            String text = toHtml((Spanned) charSequence);
            return fromHtml(Utils.replaceIcons(new StringBuilder((text))).toString());
        }
        String text = charSequence.toString();
        return Utils.replaceIcons(new StringBuilder(text));
    }

    public static final void setIcon(TextView textView, IconValue value) {
        textView.setTypeface(getTypeface(textView.getContext()));
        textView.setText(valueOf(value.character));
    }

    /**
     * The typeface that contains FontAwesome icons.
     *
     * @return the typeface, or null if something goes wrong.
     */
    public static final Typeface getTypeface(Context context) {
        if (typeface == null) {
            try {
                typeface = Typeface.createFromFile(Utils.resourceToFile(context, TTF_FILE));
            } catch (IOException e) {
                return null;
            }
        }
        return typeface;
    }

    private static Typeface getTypefaceEditMode() {
        if (typeface == null) {
            File file = new File("/system/fonts/" + TTF_FILE);
            typeface = Typeface.createFromFile(file);
        }
        return typeface;
    }

    public static enum IconValue {

        zmdi_3d_rotation('\uf101'),
        zmdi_airplane_off('\uf102'),
        zmdi_airplane('\uf103'),
        zmdi_album('\uf104'),
        zmdi_archive('\uf105'),
        zmdi_assignment_account('\uf106'),
        zmdi_assignment_alert('\uf107'),
        zmdi_assignment_check('\uf108'),
        zmdi_assignment_o('\uf109'),
        zmdi_assignment_return('\uf10a'),
        zmdi_assignment_returned('\uf10b'),
        zmdi_assignment('\uf10c'),
        zmdi_attachment_alt('\uf10d'),
        zmdi_attachment('\uf10e'),
        zmdi_audio('\uf10f'),
        zmdi_badge_check('\uf110'),
        zmdi_balance_wallet('\uf111'),
        zmdi_balance('\uf112'),
        zmdi_battery_alert('\uf113'),
        zmdi_battery_flash('\uf114'),
        zmdi_battery_unknown('\uf115'),
        zmdi_battery('\uf116'),
        zmdi_bike('\uf117'),
        zmdi_block_alt('\uf118'),
        zmdi_block('\uf119'),
        zmdi_boat('\uf11a'),
        zmdi_book_image('\uf11b'),
        zmdi_book('\uf11c'),
        zmdi_bookmark_outline('\uf11d'),
        zmdi_bookmark('\uf11e'),
        zmdi_brush('\uf11f'),
        zmdi_bug('\uf120'),
        zmdi_bus('\uf121'),
        zmdi_cake('\uf122'),
        zmdi_car_taxi('\uf123'),
        zmdi_car_wash('\uf124'),
        zmdi_car('\uf125'),
        zmdi_card_giftcard('\uf126'),
        zmdi_card_membership('\uf127'),
        zmdi_card_travel('\uf128'),
        zmdi_card('\uf129'),
        zmdi_case_check('\uf12a'),
        zmdi_case_download('\uf12b'),
        zmdi_case_play('\uf12c'),
        zmdi_case('\uf12d'),
        zmdi_cast_connected('\uf12e'),
        zmdi_cast('\uf12f'),
        zmdi_chart_donut('\uf130'),
        zmdi_chart('\uf131'),
        zmdi_city_alt('\uf132'),
        zmdi_city('\uf133'),
        zmdi_close_circle_o('\uf134'),
        zmdi_close_circle('\uf135'),
        zmdi_close('\uf136'),
        zmdi_cocktail('\uf137'),
        zmdi_code_setting('\uf138'),
        zmdi_code_smartphone('\uf139'),
        zmdi_code('\uf13a'),
        zmdi_coffee('\uf13b'),
        zmdi_collection_bookmark('\uf13c'),
        zmdi_collection_case_play('\uf13d'),
        zmdi_collection_folder_image('\uf13e'),
        zmdi_collection_image_o('\uf13f'),
        zmdi_collection_image('\uf140'),
        zmdi_collection_item_1('\uf141'),
        zmdi_collection_item_2('\uf142'),
        zmdi_collection_item_3('\uf143'),
        zmdi_collection_item_4('\uf144'),
        zmdi_collection_item_5('\uf145'),
        zmdi_collection_item_6('\uf146'),
        zmdi_collection_item_7('\uf147'),
        zmdi_collection_item_8('\uf148'),
        zmdi_collection_item_9_plus('\uf149'),
        zmdi_collection_item_9('\uf14a'),
        zmdi_collection_item('\uf14b'),
        zmdi_collection_music('\uf14c'),
        zmdi_collection_pdf('\uf14d'),
        zmdi_collection_plus('\uf14e'),
        zmdi_collection_speaker('\uf14f'),
        zmdi_collection_text('\uf150'),
        zmdi_collection_video('\uf151'),
        zmdi_compass('\uf152'),
        zmdi_cutlery('\uf153'),
        zmdi_delete('\uf154'),
        zmdi_dialpad('\uf155'),
        zmdi_dns('\uf156'),
        zmdi_drink('\uf157'),
        zmdi_edit('\uf158'),
        zmdi_email_open('\uf159'),
        zmdi_email('\uf15a'),
        zmdi_eye_off('\uf15b'),
        zmdi_eye('\uf15c'),
        zmdi_eyedropper('\uf15d'),
        zmdi_favorite_outline('\uf15e'),
        zmdi_favorite('\uf15f'),
        zmdi_filter_list('\uf160'),
        zmdi_fire('\uf161'),
        zmdi_flag('\uf162'),
        zmdi_flare('\uf163'),
        zmdi_flash_auto('\uf164'),
        zmdi_flash_off('\uf165'),
        zmdi_flash('\uf166'),
        zmdi_flip('\uf167'),
        zmdi_flower_alt('\uf168'),
        zmdi_flower('\uf169'),
        zmdi_font('\uf16a'),
        zmdi_fullscreen_alt('\uf16b'),
        zmdi_fullscreen_exit('\uf16c'),
        zmdi_fullscreen('\uf16d'),
        zmdi_functions('\uf16e'),
        zmdi_gas_station('\uf16f'),
        zmdi_gesture('\uf170'),
        zmdi_globe_alt('\uf171'),
        zmdi_globe_lock('\uf172'),
        zmdi_globe('\uf173'),
        zmdi_graduation_cap('\uf174'),
        zmdi_home('\uf175'),
        zmdi_hospital_alt('\uf176'),
        zmdi_hospital('\uf177'),
        zmdi_hotel('\uf178'),
        zmdi_hourglass_alt('\uf179'),
        zmdi_hourglass_outline('\uf17a'),
        zmdi_hourglass('\uf17b'),
        zmdi_http('\uf17c'),
        zmdi_image_alt('\uf17d'),
        zmdi_image_o('\uf17e'),
        zmdi_image('\uf17f'),
        zmdi_inbox('\uf180'),
        zmdi_invert_colors_off('\uf181'),
        zmdi_invert_colors('\uf182'),
        zmdi_key('\uf183'),
        zmdi_label_alt_outline('\uf184'),
        zmdi_label_alt('\uf185'),
        zmdi_label_heart('\uf186'),
        zmdi_label('\uf187'),
        zmdi_labels('\uf188'),
        zmdi_lamp('\uf189'),
        zmdi_landscape('\uf18a'),
        zmdi_layers_off('\uf18b'),
        zmdi_layers('\uf18c'),
        zmdi_library('\uf18d'),
        zmdi_link('\uf18e'),
        zmdi_lock_open('\uf18f'),
        zmdi_lock_outline('\uf190'),
        zmdi_lock('\uf191'),
        zmdi_mail_reply_all('\uf192'),
        zmdi_mail_reply('\uf193'),
        zmdi_mail_send('\uf194'),
        zmdi_mall('\uf195'),
        zmdi_map('\uf196'),
        zmdi_menu('\uf197'),
        zmdi_money_box('\uf198'),
        zmdi_money_off('\uf199'),
        zmdi_money('\uf19a'),
        zmdi_more_vert('\uf19b'),
        zmdi_more('\uf19c'),
        zmdi_movie_alt('\uf19d'),
        zmdi_movie('\uf19e'),
        zmdi_nature_people('\uf19f'),
        zmdi_nature('\uf1a0'),
        zmdi_navigation('\uf1a1'),
        zmdi_open_in_browser('\uf1a2'),
        zmdi_open_in_new('\uf1a3'),
        zmdi_palette('\uf1a4'),
        zmdi_parking('\uf1a5'),
        zmdi_pin_account('\uf1a6'),
        zmdi_pin_assistant('\uf1a7'),
        zmdi_pin_drop('\uf1a8'),
        zmdi_pin_help('\uf1a9'),
        zmdi_pin_off('\uf1aa'),
        zmdi_pin('\uf1ab'),
        zmdi_pizza('\uf1ac'),
        zmdi_plaster('\uf1ad'),
        zmdi_power_setting('\uf1ae'),
        zmdi_power('\uf1af'),
        zmdi_print('\uf1b0'),
        zmdi_puzzle_piece('\uf1b1'),
        zmdi_quote('\uf1b2'),
        zmdi_railway('\uf1b3'),
        zmdi_receipt('\uf1b4'),
        zmdi_refresh_alt('\uf1b5'),
        zmdi_refresh_sync_alert('\uf1b6'),
        zmdi_refresh_sync_off('\uf1b7'),
        zmdi_refresh_sync('\uf1b8'),
        zmdi_refresh('\uf1b9'),
        zmdi_roller('\uf1ba'),
        zmdi_ruler('\uf1bb'),
        zmdi_scissors('\uf1bc'),
        zmdi_screen_rotation_lock('\uf1bd'),
        zmdi_screen_rotation('\uf1be'),
        zmdi_search_for('\uf1bf'),
        zmdi_search_in_file('\uf1c0'),
        zmdi_search_in_page('\uf1c1'),
        zmdi_search_replace('\uf1c2'),
        zmdi_search('\uf1c3'),
        zmdi_seat('\uf1c4'),
        zmdi_settings_square('\uf1c5'),
        zmdi_settings('\uf1c6'),
        zmdi_shield_check('\uf1c7'),
        zmdi_shield_security('\uf1c8'),
        zmdi_shopping_basket('\uf1c9'),
        zmdi_shopping_cart_plus('\uf1ca'),
        zmdi_shopping_cart('\uf1cb'),
        zmdi_sign_in('\uf1cc'),
        zmdi_sort_amount_asc('\uf1cd'),
        zmdi_sort_amount_desc('\uf1ce'),
        zmdi_sort_asc('\uf1cf'),
        zmdi_sort_desc('\uf1d0'),
        zmdi_spellcheck('\uf1d1'),
        zmdi_storage('\uf1d2'),
        zmdi_store_24('\uf1d3'),
        zmdi_store('\uf1d4'),
        zmdi_subway('\uf1d5'),
        zmdi_sun('\uf1d6'),
        zmdi_tab_unselected('\uf1d7'),
        zmdi_tab('\uf1d8'),
        zmdi_tag_close('\uf1d9'),
        zmdi_tag_more('\uf1da'),
        zmdi_tag('\uf1db'),
        zmdi_thumb_down('\uf1dc'),
        zmdi_thumb_up_down('\uf1dd'),
        zmdi_thumb_up('\uf1de'),
        zmdi_ticket_star('\uf1df'),
        zmdi_toll('\uf1e0'),
        zmdi_toys('\uf1e1'),
        zmdi_traffic('\uf1e2'),
        zmdi_translate('\uf1e3'),
        zmdi_triangle_down('\uf1e4'),
        zmdi_triangle_up('\uf1e5'),
        zmdi_truck('\uf1e6'),
        zmdi_turning_sign('\uf1e7'),
        zmdi_wallpaper('\uf1e8'),
        zmdi_washing_machine('\uf1e9'),
        zmdi_window_maximize('\uf1ea'),
        zmdi_window_minimize('\uf1eb'),
        zmdi_window_restore('\uf1ec'),
        zmdi_wrench('\uf1ed'),
        zmdi_zoom_in('\uf1ee'),
        zmdi_zoom_out('\uf1ef'),
        zmdi_alert_circle_o('\uf1f0'),
        zmdi_alert_circle('\uf1f1'),
        zmdi_alert_octagon('\uf1f2'),
        zmdi_alert_polygon('\uf1f3'),
        zmdi_alert_triangle('\uf1f4'),
        zmdi_help_outline('\uf1f5'),
        zmdi_help('\uf1f6'),
        zmdi_info_outline('\uf1f7'),
        zmdi_info('\uf1f8'),
        zmdi_notifications_active('\uf1f9'),
        zmdi_notifications_add('\uf1fa'),
        zmdi_notifications_none('\uf1fb'),
        zmdi_notifications_off('\uf1fc'),
        zmdi_notifications_paused('\uf1fd'),
        zmdi_notifications('\uf1fe'),
        zmdi_account_add('\uf1ff'),
        zmdi_account_box_mail('\uf200'),
        zmdi_account_box_o('\uf201'),
        zmdi_account_box_phone('\uf202'),
        zmdi_account_box('\uf203'),
        zmdi_account_calendar('\uf204'),
        zmdi_account_circle('\uf205'),
        zmdi_account_o('\uf206'),
        zmdi_account('\uf207'),
        zmdi_accounts_add('\uf208'),
        zmdi_accounts_alt('\uf209'),
        zmdi_accounts_list_alt('\uf20a'),
        zmdi_accounts_list('\uf20b'),
        zmdi_accounts_outline('\uf20c'),
        zmdi_accounts('\uf20d'),
        zmdi_face('\uf20e'),
        zmdi_female('\uf20f'),
        zmdi_male_alt('\uf210'),
        zmdi_male_female('\uf211'),
        zmdi_male('\uf212'),
        zmdi_mood_bad('\uf213'),
        zmdi_mood('\uf214'),
        zmdi_run('\uf215'),
        zmdi_walk('\uf216'),
        zmdi_cloud_box('\uf217'),
        zmdi_cloud_circle('\uf218'),
        zmdi_cloud_done('\uf219'),
        zmdi_cloud_download('\uf21a'),
        zmdi_cloud_off('\uf21b'),
        zmdi_cloud_outline_alt('\uf21c'),
        zmdi_cloud_outline('\uf21d'),
        zmdi_cloud_upload('\uf21e'),
        zmdi_cloud('\uf21f'),
        zmdi_download('\uf220'),
        zmdi_file_plus('\uf221'),
        zmdi_file_text('\uf222'),
        zmdi_file('\uf223'),
        zmdi_folder_outline('\uf224'),
        zmdi_folder_person('\uf225'),
        zmdi_folder_star_alt('\uf226'),
        zmdi_folder_star('\uf227'),
        zmdi_folder('\uf228'),
        zmdi_gif('\uf229'),
        zmdi_upload('\uf22a'),
        zmdi_border_all('\uf22b'),
        zmdi_border_bottom('\uf22c'),
        zmdi_border_clear('\uf22d'),
        zmdi_border_color('\uf22e'),
        zmdi_border_horizontal('\uf22f'),
        zmdi_border_inner('\uf230'),
        zmdi_border_left('\uf231'),
        zmdi_border_outer('\uf232'),
        zmdi_border_right('\uf233'),
        zmdi_border_style('\uf234'),
        zmdi_border_top('\uf235'),
        zmdi_border_vertical('\uf236'),
        zmdi_copy('\uf237'),
        zmdi_crop('\uf238'),
        zmdi_format_align_center('\uf239'),
        zmdi_format_align_justify('\uf23a'),
        zmdi_format_align_left('\uf23b'),
        zmdi_format_align_right('\uf23c'),
        zmdi_format_bold('\uf23d'),
        zmdi_format_clear_all('\uf23e'),
        zmdi_format_clear('\uf23f'),
        zmdi_format_color_fill('\uf240'),
        zmdi_format_color_reset('\uf241'),
        zmdi_format_color_text('\uf242'),
        zmdi_format_indent_decrease('\uf243'),
        zmdi_format_indent_increase('\uf244'),
        zmdi_format_italic('\uf245'),
        zmdi_format_line_spacing('\uf246'),
        zmdi_format_list_bulleted('\uf247'),
        zmdi_format_list_numbered('\uf248'),
        zmdi_format_ltr('\uf249'),
        zmdi_format_rtl('\uf24a'),
        zmdi_format_size('\uf24b'),
        zmdi_format_strikethrough_s('\uf24c'),
        zmdi_format_strikethrough('\uf24d'),
        zmdi_format_subject('\uf24e'),
        zmdi_format_underlined('\uf24f'),
        zmdi_format_valign_bottom('\uf250'),
        zmdi_format_valign_center('\uf251'),
        zmdi_format_valign_top('\uf252'),
        zmdi_redo('\uf253'),
        zmdi_select_all('\uf254'),
        zmdi_space_bar('\uf255'),
        zmdi_text_format('\uf256'),
        zmdi_transform('\uf257'),
        zmdi_undo('\uf258'),
        zmdi_wrap_text('\uf259'),
        zmdi_comment_alert('\uf25a'),
        zmdi_comment_alt_text('\uf25b'),
        zmdi_comment_alt('\uf25c'),
        zmdi_comment_edit('\uf25d'),
        zmdi_comment_image('\uf25e'),
        zmdi_comment_list('\uf25f'),
        zmdi_comment_more('\uf260'),
        zmdi_comment_outline('\uf261'),
        zmdi_comment_text_alt('\uf262'),
        zmdi_comment_text('\uf263'),
        zmdi_comment_video('\uf264'),
        zmdi_comment('\uf265'),
        zmdi_comments('\uf266'),
        zmdi_check_all('\uf267'),
        zmdi_check_circle_u('\uf268'),
        zmdi_check_circle('\uf269'),
        zmdi_check_square('\uf26a'),
        zmdi_check('\uf26b'),
        zmdi_circle_o('\uf26c'),
        zmdi_circle('\uf26d'),
        zmdi_dot_circle_alt('\uf26e'),
        zmdi_dot_circle('\uf26f'),
        zmdi_minus_circle_outline('\uf270'),
        zmdi_minus_circle('\uf271'),
        zmdi_minus_square('\uf272'),
        zmdi_minus('\uf273'),
        zmdi_plus_circle_o_duplicate('\uf274'),
        zmdi_plus_circle_o('\uf275'),
        zmdi_plus_circle('\uf276'),
        zmdi_plus_square('\uf277'),
        zmdi_plus('\uf278'),
        zmdi_square_o('\uf279'),
        zmdi_star_circle('\uf27a'),
        zmdi_star_half('\uf27b'),
        zmdi_star_outline('\uf27c'),
        zmdi_star('\uf27d'),
        zmdi_bluetooth_connected('\uf27e'),
        zmdi_bluetooth_off('\uf27f'),
        zmdi_bluetooth_search('\uf280'),
        zmdi_bluetooth_setting('\uf281'),
        zmdi_bluetooth('\uf282'),
        zmdi_camera_add('\uf283'),
        zmdi_camera_alt('\uf284'),
        zmdi_camera_bw('\uf285'),
        zmdi_camera_front('\uf286'),
        zmdi_camera_mic('\uf287'),
        zmdi_camera_party_mode('\uf288'),
        zmdi_camera_rear('\uf289'),
        zmdi_camera_roll('\uf28a'),
        zmdi_camera_switch('\uf28b'),
        zmdi_camera('\uf28c'),
        zmdi_card_alert('\uf28d'),
        zmdi_card_off('\uf28e'),
        zmdi_card_sd('\uf28f'),
        zmdi_card_sim('\uf290'),
        zmdi_desktop_mac('\uf291'),
        zmdi_desktop_windows('\uf292'),
        zmdi_device_hub('\uf293'),
        zmdi_devices_off('\uf294'),
        zmdi_devices('\uf295'),
        zmdi_dock('\uf296'),
        zmdi_floppy('\uf297'),
        zmdi_gamepad('\uf298'),
        zmdi_gps_dot('\uf299'),
        zmdi_gps_off('\uf29a'),
        zmdi_gps('\uf29b'),
        zmdi_headset_mic('\uf29c'),
        zmdi_headset('\uf29d'),
        zmdi_input_antenna('\uf29e'),
        zmdi_input_composite('\uf29f'),
        zmdi_input_hdmi('\uf2a0'),
        zmdi_input_power('\uf2a1'),
        zmdi_input_svideo('\uf2a2'),
        zmdi_keyboard_hide('\uf2a3'),
        zmdi_keyboard('\uf2a4'),
        zmdi_laptop_chromebook('\uf2a5'),
        zmdi_laptop_mac('\uf2a6'),
        zmdi_laptop('\uf2a7'),
        zmdi_mic_off('\uf2a8'),
        zmdi_mic_outline('\uf2a9'),
        zmdi_mic_setting('\uf2aa'),
        zmdi_mic('\uf2ab'),
        zmdi_mouse('\uf2ac'),
        zmdi_network_alert('\uf2ad'),
        zmdi_network_locked('\uf2ae'),
        zmdi_network_off('\uf2af'),
        zmdi_network_outline('\uf2b0'),
        zmdi_network_setting('\uf2b1'),
        zmdi_network('\uf2b2'),
        zmdi_phone_bluetooth('\uf2b3'),
        zmdi_phone_end('\uf2b4'),
        zmdi_phone_forwarded('\uf2b5'),
        zmdi_phone_in_talk('\uf2b6'),
        zmdi_phone_locked('\uf2b7'),
        zmdi_phone_missed('\uf2b8'),
        zmdi_phone_msg('\uf2b9'),
        zmdi_phone_paused('\uf2ba'),
        zmdi_phone_ring('\uf2bb'),
        zmdi_phone_setting('\uf2bc'),
        zmdi_phone_sip('\uf2bd'),
        zmdi_phone('\uf2be'),
        zmdi_portable_wifi_changes('\uf2bf'),
        zmdi_portable_wifi_off('\uf2c0'),
        zmdi_portable_wifi('\uf2c1'),
        zmdi_radio('\uf2c2'),
        zmdi_reader('\uf2c3'),
        zmdi_remote_control_alt('\uf2c4'),
        zmdi_remote_control('\uf2c5'),
        zmdi_router('\uf2c6'),
        zmdi_scanner('\uf2c7'),
        zmdi_smartphone_android('\uf2c8'),
        zmdi_smartphone_download('\uf2c9'),
        zmdi_smartphone_erase('\uf2ca'),
        zmdi_smartphone_info('\uf2cb'),
        zmdi_smartphone_iphone('\uf2cc'),
        zmdi_smartphone_landscape_lock('\uf2cd'),
        zmdi_smartphone_landscape('\uf2ce'),
        zmdi_smartphone_lock('\uf2cf'),
        zmdi_smartphone_portrait_lock('\uf2d0'),
        zmdi_smartphone_ring('\uf2d1'),
        zmdi_smartphone_setting('\uf2d2'),
        zmdi_smartphone_setup('\uf2d3'),
        zmdi_smartphone('\uf2d4'),
        zmdi_speaker('\uf2d5'),
        zmdi_tablet_android('\uf2d6'),
        zmdi_tablet_mac('\uf2d7'),
        zmdi_tablet('\uf2d8'),
        zmdi_tv_alt_play('\uf2d9'),
        zmdi_tv_list('\uf2da'),
        zmdi_tv_play('\uf2db'),
        zmdi_tv('\uf2dc'),
        zmdi_usb('\uf2dd'),
        zmdi_videocam_off('\uf2de'),
        zmdi_videocam_switch('\uf2df'),
        zmdi_videocam('\uf2e0'),
        zmdi_watch('\uf2e1'),
        zmdi_wifi_alt_2('\uf2e2'),
        zmdi_wifi_alt('\uf2e3'),
        zmdi_wifi_info('\uf2e4'),
        zmdi_wifi_lock('\uf2e5'),
        zmdi_wifi_off('\uf2e6'),
        zmdi_wifi_outline('\uf2e7'),
        zmdi_wifi('\uf2e8'),
        zmdi_arrow_left_bottom('\uf2e9'),
        zmdi_arrow_left('\uf2ea'),
        zmdi_arrow_merge('\uf2eb'),
        zmdi_arrow_missed('\uf2ec'),
        zmdi_arrow_right_top('\uf2ed'),
        zmdi_arrow_right('\uf2ee'),
        zmdi_arrow_split('\uf2ef'),
        zmdi_arrows('\uf2f0'),
        zmdi_caret_down_circle('\uf2f1'),
        zmdi_caret_down('\uf2f2'),
        zmdi_caret_left_circle('\uf2f3'),
        zmdi_caret_left('\uf2f4'),
        zmdi_caret_right_circle('\uf2f5'),
        zmdi_caret_right('\uf2f6'),
        zmdi_caret_up_circle('\uf2f7'),
        zmdi_caret_up('\uf2f8'),
        zmdi_chevron_down('\uf2f9'),
        zmdi_chevron_left('\uf2fa'),
        zmdi_chevron_right('\uf2fb'),
        zmdi_chevron_up('\uf2fc'),
        zmdi_forward('\uf2fd'),
        zmdi_long_arrow_down('\uf2fe'),
        zmdi_long_arrow_left('\uf2ff'),
        zmdi_long_arrow_return('\uf300'),
        zmdi_long_arrow_right('\uf301'),
        zmdi_long_arrow_tab('\uf302'),
        zmdi_long_arrow_up('\uf303'),
        zmdi_rotate_ccw('\uf304'),
        zmdi_rotate_cw('\uf305'),
        zmdi_rotate_left('\uf306'),
        zmdi_rotate_right('\uf307'),
        zmdi_square_down('\uf308'),
        zmdi_square_right('\uf309'),
        zmdi_swap_alt('\uf30a'),
        zmdi_swap_vertical_circle('\uf30b'),
        zmdi_swap_vertical('\uf30c'),
        zmdi_swap('\uf30d'),
        zmdi_trending_down('\uf30e'),
        zmdi_trending_flat('\uf30f'),
        zmdi_trending_up('\uf310'),
        zmdi_unfold_less('\uf311'),
        zmdi_unfold_more('\uf312'),
        zmdi_apps('\uf313'),
        zmdi_grid_off('\uf314'),
        zmdi_grid('\uf315'),
        zmdi_view_agenda('\uf316'),
        zmdi_view_array('\uf317'),
        zmdi_view_carousel('\uf318'),
        zmdi_view_column('\uf319'),
        zmdi_view_comfy('\uf31a'),
        zmdi_view_compact('\uf31b'),
        zmdi_view_dashboard('\uf31c'),
        zmdi_view_day('\uf31d'),
        zmdi_view_headline('\uf31e'),
        zmdi_view_list_alt('\uf31f'),
        zmdi_view_list('\uf320'),
        zmdi_view_module('\uf321'),
        zmdi_view_quilt('\uf322'),
        zmdi_view_stream('\uf323'),
        zmdi_view_subtitles('\uf324'),
        zmdi_view_toc('\uf325'),
        zmdi_view_web('\uf326'),
        zmdi_view_week('\uf327'),
        zmdi_widgets('\uf328'),
        zmdi_alarm_check('\uf329'),
        zmdi_alarm_off('\uf32a'),
        zmdi_alarm_plus('\uf32b'),
        zmdi_alarm_snooze('\uf32c'),
        zmdi_alarm('\uf32d'),
        zmdi_calendar_alt('\uf32e'),
        zmdi_calendar_check('\uf32f'),
        zmdi_calendar_close('\uf330'),
        zmdi_calendar_note('\uf331'),
        zmdi_calendar('\uf332'),
        zmdi_time_countdown('\uf333'),
        zmdi_time_interval('\uf334'),
        zmdi_time_restore_setting('\uf335'),
        zmdi_time_restore('\uf336'),
        zmdi_time('\uf337'),
        zmdi_timer_off('\uf338'),
        zmdi_timer('\uf339'),
        zmdi_android_alt('\uf33a'),
        zmdi_android('\uf33b'),
        zmdi_apple('\uf33c'),
        zmdi_behance('\uf33d'),
        zmdi_codepen('\uf33e'),
        zmdi_dribbble('\uf33f'),
        zmdi_dropbox('\uf340'),
        zmdi_evernote('\uf341'),
        zmdi_facebook_box('\uf342'),
        zmdi_facebook('\uf343'),
        zmdi_github_box('\uf344'),
        zmdi_github('\uf345'),
        zmdi_google_drive('\uf346'),
        zmdi_google_earth('\uf347'),
        zmdi_google_glass('\uf348'),
        zmdi_google_maps('\uf349'),
        zmdi_google_pages('\uf34a'),
        zmdi_google_play('\uf34b'),
        zmdi_google_plus_box('\uf34c'),
        zmdi_google_plus('\uf34d'),
        zmdi_google('\uf34e'),
        zmdi_instagram('\uf34f'),
        zmdi_language_css3('\uf350'),
        zmdi_language_html5('\uf351'),
        zmdi_language_javascript('\uf352'),
        zmdi_language_python_alt('\uf353'),
        zmdi_language_python('\uf354'),
        zmdi_lastfm('\uf355'),
        zmdi_linkedin_box('\uf356'),
        zmdi_paypal('\uf357'),
        zmdi_pinterest_box('\uf358'),
        zmdi_pocket('\uf359'),
        zmdi_polymer('\uf35a'),
        zmdi_share('\uf35b'),
        zmdi_stack_overflow('\uf35c'),
        zmdi_steam_square('\uf35d'),
        zmdi_steam('\uf35e'),
        zmdi_twitter_box('\uf35f'),
        zmdi_twitter('\uf360'),
        zmdi_vk('\uf361'),
        zmdi_wikipedia('\uf362'),
        zmdi_windows('\uf363'),
        zmdi_aspect_ratio_alt('\uf364'),
        zmdi_aspect_ratio('\uf365'),
        zmdi_blur_circular('\uf366'),
        zmdi_blur_linear('\uf367'),
        zmdi_blur_off('\uf368'),
        zmdi_blur('\uf369'),
        zmdi_brightness_2('\uf36a'),
        zmdi_brightness_3('\uf36b'),
        zmdi_brightness_4('\uf36c'),
        zmdi_brightness_5('\uf36d'),
        zmdi_brightness_6('\uf36e'),
        zmdi_brightness_7('\uf36f'),
        zmdi_brightness_auto('\uf370'),
        zmdi_brightness_setting('\uf371'),
        zmdi_broken_image('\uf372'),
        zmdi_center_focus_strong('\uf373'),
        zmdi_center_focus_weak('\uf374'),
        zmdi_compare('\uf375'),
        zmdi_crop_16_9('\uf376'),
        zmdi_crop_3_2('\uf377'),
        zmdi_crop_5_4('\uf378'),
        zmdi_crop_7_5('\uf379'),
        zmdi_crop_din('\uf37a'),
        zmdi_crop_free('\uf37b'),
        zmdi_crop_landscape('\uf37c'),
        zmdi_crop_portrait('\uf37d'),
        zmdi_crop_square('\uf37e'),
        zmdi_exposure_alt('\uf37f'),
        zmdi_exposure('\uf380'),
        zmdi_filter_b_and_w('\uf381'),
        zmdi_filter_center_focus('\uf382'),
        zmdi_filter_frames('\uf383'),
        zmdi_filter_tilt_shift('\uf384'),
        zmdi_gradient('\uf385'),
        zmdi_grain('\uf386'),
        zmdi_graphic_eq('\uf387'),
        zmdi_hdr_off('\uf388'),
        zmdi_hdr_strong('\uf389'),
        zmdi_hdr_weak('\uf38a'),
        zmdi_hdr('\uf38b'),
        zmdi_iridescent('\uf38c'),
        zmdi_leak_off('\uf38d'),
        zmdi_leak('\uf38e'),
        zmdi_looks('\uf38f'),
        zmdi_loupe('\uf390'),
        zmdi_panorama_horizontal('\uf391'),
        zmdi_panorama_vertical('\uf392'),
        zmdi_panorama_wide_angle('\uf393'),
        zmdi_photo_size_select_large('\uf394'),
        zmdi_photo_size_select_small('\uf395'),
        zmdi_picture_in_picture('\uf396'),
        zmdi_slideshow('\uf397'),
        zmdi_texture('\uf398'),
        zmdi_tonality('\uf399'),
        zmdi_vignette('\uf39a'),
        zmdi_wb_auto('\uf39b'),
        zmdi_eject_alt('\uf39c'),
        zmdi_eject('\uf39d'),
        zmdi_equalizer('\uf39e'),
        zmdi_fast_forward('\uf39f'),
        zmdi_fast_rewind('\uf3a0'),
        zmdi_forward_10('\uf3a1'),
        zmdi_forward_30('\uf3a2'),
        zmdi_forward_5('\uf3a3'),
        zmdi_hearing('\uf3a4'),
        zmdi_pause_circle_outline('\uf3a5'),
        zmdi_pause_circle('\uf3a6'),
        zmdi_pause('\uf3a7'),
        zmdi_play_circle_outline('\uf3a8'),
        zmdi_play_circle('\uf3a9'),
        zmdi_play('\uf3aa'),
        zmdi_playlist_audio('\uf3ab'),
        zmdi_playlist_plus('\uf3ac'),
        zmdi_repeat_one('\uf3ad'),
        zmdi_repeat('\uf3ae'),
        zmdi_replay_10('\uf3af'),
        zmdi_replay_30('\uf3b0'),
        zmdi_replay_5('\uf3b1'),
        zmdi_replay('\uf3b2'),
        zmdi_shuffle('\uf3b3'),
        zmdi_skip_next('\uf3b4'),
        zmdi_skip_previous('\uf3b5'),
        zmdi_stop('\uf3b6'),
        zmdi_surround_sound('\uf3b7'),
        zmdi_tune('\uf3b8'),
        zmdi_volume_down('\uf3b9'),
        zmdi_volume_mute('\uf3ba'),
        zmdi_volume_off('\uf3bb'),
        zmdi_volume_up('\uf3bc'),
        zmdi_n_1_square('\uf3bd'),
        zmdi_n_2_square('\uf3be'),
        zmdi_n_3_square('\uf3bf'),
        zmdi_n_4_square('\uf3c0'),
        zmdi_n_5_square('\uf3c1'),
        zmdi_n_6_square('\uf3c2'),
        zmdi_neg_1('\uf3c3'),
        zmdi_neg_2('\uf3c4'),
        zmdi_plus_1('\uf3c5'),
        zmdi_plus_2('\uf3c6'),
        zmdi_sec_10('\uf3c7'),
        zmdi_sec_3('\uf3c8'),
        zmdi_zero('\uf3c9'),
        zmdi_airline_seat_flat_angled('\uf3ca'),
        zmdi_airline_seat_flat('\uf3cb'),
        zmdi_airline_seat_individual_suite('\uf3cc'),
        zmdi_airline_seat_legroom_extra('\uf3cd'),
        zmdi_airline_seat_legroom_normal('\uf3ce'),
        zmdi_airline_seat_legroom_reduced('\uf3cf'),
        zmdi_airline_seat_recline_extra('\uf3d0'),
        zmdi_airline_seat_recline_normal('\uf3d1'),
        zmdi_airplay('\uf3d2'),
        zmdi_closed_caption('\uf3d3'),
        zmdi_confirmation_number('\uf3d4'),
        zmdi_developer_board('\uf3d5'),
        zmdi_disc_full('\uf3d6'),
        zmdi_explicit('\uf3d7'),
        zmdi_flight_land('\uf3d8'),
        zmdi_flight_takeoff('\uf3d9'),
        zmdi_flip_to_back('\uf3da'),
        zmdi_flip_to_front('\uf3db'),
        zmdi_group_work('\uf3dc'),
        zmdi_hd('\uf3dd'),
        zmdi_hq('\uf3de'),
        zmdi_markunread_mailbox('\uf3df'),
        zmdi_memory('\uf3e0'),
        zmdi_nfc('\uf3e1'),
        zmdi_play_for_work('\uf3e2'),
        zmdi_power_input('\uf3e3'),
        zmdi_present_to_all('\uf3e4'),
        zmdi_satellite('\uf3e5'),
        zmdi_tap_and_play('\uf3e6'),
        zmdi_vibration('\uf3e7'),
        zmdi_voicemail('\uf3e8'),
        zmdi_import_export('\uf30c'),
        zmdi_swap_vertical_('\uf30c'),
        zmdi_airplanemode_inactive('\uf102'),
        zmdi_airplanemode_active('\uf103'),
        zmdi_rate_review('\uf103'),
        zmdi_comment_sign('\uf25a'),
        zmdi_network_warning('\uf2ad'),
        zmdi_shopping_cart_add('\uf1ca'),
        zmdi_file_add('\uf221'),
        zmdi_network_wifi_scan('\uf2e4'),
        zmdi_collection_add('\uf14e'),
        zmdi_format_playlist_add('\uf3ac'),
        zmdi_format_queue_music('\uf3ab'),
        zmdi_plus_box('\uf277'),
        zmdi_tag_backspace('\uf1d9'),
        zmdi_alarm_add('\uf32b'),
        zmdi_battery_charging('\uf114'),
        zmdi_daydream_setting('\uf217'),
        zmdi_more_horiz('\uf19c'),
        zmdi_book_photo('\uf11b'),
        zmdi_incandescent('\uf189'),
        zmdi_wb_iridescent('\uf38c'),
        zmdi_calendar_remove('\uf330'),
        zmdi_refresh_sync_disabled('\uf1b7'),
        zmdi_refresh_sync_problem('\uf1b6'),
        zmdi_crop_original('\uf17e'),
        zmdi_power_off('\uf1af'),
        zmdi_power_off_setting('\uf1ae'),
        zmdi_leak_remove('\uf38d'),
        zmdi_star_border('\uf27c'),
        zmdi_brightness_low('\uf36d'),
        zmdi_brightness_medium('\uf36e'),
        zmdi_brightness_high('\uf36f'),
        zmdi_smartphone_portrait('\uf2d4'),
        zmdi_live_tv('\uf2d9'),
        zmdi_format_textdirection_l_to_r('\uf249'),
        zmdi_format_textdirection_r_to_l('\uf24a'),
        zmdi_arrow_back('\uf2ea'),
        zmdi_arrow_forward('\uf2ee'),
        zmdi_arrow_in('\uf2e9'),
        zmdi_arrow_out('\uf2ed'),
        zmdi_rotate_90_degrees_ccw('\uf304'),
        zmdi_adb('\uf33a'),
        zmdi_network_wifi('\uf2e8'),
        zmdi_network_wifi_alt('\uf2e3'),
        zmdi_network_wifi_lock('\uf2e5'),
        zmdi_network_wifi_off('\uf2e6'),
        zmdi_network_wifi_outline('\uf2e7'),
        zmdi_network_wifi_info('\uf2e4'),
        zmdi_layers_clear('\uf18b'),
        zmdi_colorize('\uf15d'),
        zmdi_format_paint('\uf1ba'),
        zmdi_format_quote('\uf1b2'),
        zmdi_camera_monochrome_photos('\uf285'),
        zmdi_sort_by_alpha('\uf1cf'),
        zmdi_folder_shared('\uf225'),
        zmdi_folder_special('\uf226'),
        zmdi_comment_dots('\uf260'),
        zmdi_reorder('\uf31e'),
        zmdi_dehaze('\uf197'),
        zmdi_sort('\uf1ce'),
        zmdi_pages('\uf34a'),
        zmdi_calendar_account('\uf204'),
        zmdi_paste('\uf109'),
        zmdi_cut('\uf1bc'),
        zmdi_save('\uf297'),
        zmdi_smartphone_code('\uf139'),
        zmdi_directions_bike('\uf117'),
        zmdi_directions_boat('\uf11a'),
        zmdi_directions_bus('\uf121'),
        zmdi_directions_car('\uf125'),
        zmdi_directions_railway('\uf1b3'),
        zmdi_directions_run('\uf215'),
        zmdi_directions_subway('\uf1d5'),
        zmdi_directions_walk('\uf216'),
        zmdi_local_hotel('\uf178'),
        zmdi_local_activity('\uf1df'),
        zmdi_local_play('\uf1df'),
        zmdi_local_airport('\uf103'),
        zmdi_local_atm('\uf198'),
        zmdi_local_bar('\uf137'),
        zmdi_local_cafe('\uf13b'),
        zmdi_local_car_wash('\uf124'),
        zmdi_local_convenience_store('\uf1d3'),
        zmdi_local_dining('\uf153'),
        zmdi_local_drink('\uf157'),
        zmdi_local_florist('\uf168'),
        zmdi_local_gas_station('\uf16f'),
        zmdi_local_grocery_store('\uf1cb'),
        zmdi_local_hospital('\uf177'),
        zmdi_local_laundry_service('\uf1e9'),
        zmdi_local_library('\uf18d'),
        zmdi_local_mall('\uf195'),
        zmdi_local_movies('\uf19d'),
        zmdi_local_offer('\uf187'),
        zmdi_local_parking('\uf1a5'),
        zmdi_local_pharmacy('\uf176'),
        zmdi_local_phone('\uf2be'),
        zmdi_local_pizza('\uf1ac'),
        zmdi_local_post_office('\uf15a'),
        zmdi_local_printshop('\uf1b0'),
        zmdi_local_see('\uf28c'),
        zmdi_local_shipping('\uf1e6'),
        zmdi_local_store('\uf1d4'),
        zmdi_local_taxi('\uf123'),
        zmdi_local_wc('\uf211'),
        zmdi_my_location('\uf299'),
        zmdi_directions('\uf1e7');

        public char character;

        IconValue(char character) {
            this.character = character;
        }

        public String formattedName() {
            return "{" + name() + "}";
        }

        public char character() {
            return character;
        }
    }
}
