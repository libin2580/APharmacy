package com.meridian.cybraum.zulekha.alexis.alexispharmacy;


public class Health_tips_Model {
 String tip_title,tip_content,image;

 public void settip_title(String tip_title) {
  this.tip_title = tip_title;
 }

 public void settip_content(String tip_content) {
  this.tip_content = tip_content;
 }

 public String gettip_content() {
  return tip_content;
 }

 public String gettip_title() {
  return tip_title;
 }

 public String getimage() {
  return image;
 }

 public void setimage(String image) {
  this.image = image;
 }
}