package org.virus.mypass.ui;

import org.json.JSONObject;

/**
 *
 * @author SecVirus
 */
public interface categories {
    JSONObject icons = new JSONObject()
            .put( "ID card (fill)", "ID_CARD")
            .put("ID card (outline)", "ID_CARD_O")
            .put("ID badge (outline)", "ID_BADGE")
            .put("Ambulance", "AMBULANCE")
            .put("Sign language", "AMERICAN_SIGN_LANGUAGE_INTERPRETING")
            .put("Amazon", "AMAZON")
            .put("Book (fill)", "ADDRESS_BOOK")
            .put("Book (outline)", "ADDRESS_BOOK_O")
            .put("Android", "ANDROID")
            .put("Apple", "APPLE")
            .put("Archive", "ARCHIVE")
            .put("Mail", "AT")
            .put("Law", "BALANCE_SCALE")
            .put("Ban", "BAN")
            .put("Barcode", "BARCODE")
            .put("Shower", "BATH")
            .put("Battery (4/4)", "BATTERY_FULL")
            .put("Battery (3/4)", "BATTERY_THREE_QUARTERS")
            .put("Battery (2/4)", "BATTERY_HALF")
            .put("Battery (1/4)", "BATTERY_QUARTER")
            .put("Battery (0/4)", "BATTERY_EMPTY")
            .put("Sleep", "BED")
            .put("Behance", "BEHANCE")
            .put("Behance (fill)", "BEHANCE_SQUARE")
            .put("Bell (fill)", "BELL")
            .put("Bell (outline)", "BELL_O")
            .put("Muted bell (fill)", "BELL_SLASH")
            .put("Muted bell (outline)", "BELL_SLASH_O")
            .put("Bicycle", "BICYCLE")
            .put("Binoculars", "BINOCULARS")
            .put("Cake", "BIRTHDAY_CAKE")
            .put("Blind", "BLIND")
            .put("Bluetooth (fill)", "BLUETOOTH")
            .put("Bluetooth (outline)", "BLUETOOTH_B")
            .put("Bolt", "BOLT")
            .put("B", "BOLD")
            .put("Bomb", "BOMB")
            .put("Book", "BOOK")
            .put("Bookmark (fill)", "BOOKMARK")
            .put("Bookmark (outline)", "BOOKMARK_O")
            .put("Braille", "BRAILLE")
            .put("Case", "BRIEFCASE")
            .put("BTC", "BTC")
            .put("Bug", "BUG")
            .put("Building (fill)", "BUILDING")
            .put("Building (outline)", "BUILDING_O")
            .put("Bullhorn", "BULLHORN")
            .put("Bullseye", "BULLSEYE")
            .put("Bus", "BUS")
            .put("Car", "CAR")
            .put("Calculator", "CALCULATOR")
            .put("Calender (grid)", "CALENDAR")
            .put("Calender (check)", "CALENDAR_CHECK_O")
            .put("Calender (remove)", "CALENDAR_MINUS_O")
            .put("Calender (add)", "CALENDAR_PLUS_O")
            .put("Calender", "CALENDAR_O")
            .put("Calender (cancel)", "CALENDAR_TIMES_O")
            .put("Camera", "CAMERA")
            .put("CAMERA (retro)", "CAMERA_RETRO")
            .put("Cart (in)", "CART_ARROW_DOWN")
            .put("Cart (add)", "CART_PLUS")
            .put("Credit card", "CC")
            .put("Credit card (american express)", "CC_AMEX")
            .put("Credit card (diners club)", "CC_DINERS_CLUB")
            .put("Credit card (discover)", "CC_DISCOVER")
            .put("Credit card (jcb)", "CC_JCB")
            .put("Credit card (mastercard)", "CC_MASTERCARD")
            .put("Credit card (paypal)", "CC_PAYPAL")
            .put("Credit card (stripe)", "CC_STRIPE")
            .put("Credit card (visa)", "CC_VISA")
            .put("Certificate", "CERTIFICATE")
            .put("Chain (broken)", "CHAIN_BROKEN")
            .put("Check", "CHECK")
            .put("Check (fill circle)", "CHECK_CIRCLE")
            .put("Check (outline circle)", "CHECK_CIRCLE_O")
            .put("Check (fill square)", "CHECK_SQUARE")
            .put("Check (outline square)", "CHECK_SQUARE_O")
            .put("Child", "CHILD")
            .put("Chrome", "CHROME")
            .put("Circle (fill)", "CIRCLE")
            .put("Circle (outline)", "CIRCLE_O")
            .put("Circle (north outline)", "CIRCLE_O_NOTCH")
            .put("Circle (thin outline)", "CIRCLE_THIN")
            .put("Clipboard", "CLIPBOARD")
            .put("Clock", "CLOCK_O")
            .put("Clone", "CLONE")
            .put("Cloud", "CLOUD")
            .put("Cloud (download)", "CLOUD_DOWNLOAD")
            .put("Cloud (upload)", "CLOUD_UPLOAD")
            .put("Code", "CODE")
            .put("Codepen", "CODEPEN")
            .put("Code fork", "CODE_FORK")
            .put("Code pie", "CODIEPIE")
            .put("Coffee", "COFFEE")
            .put("setting", "COG")
            .put("Gear", "COGS")
            .put("Layout", "COLUMNS")
            .put("Message (fill)", "COMMENT")
            .put("Typing (fill)", "COMMENTING")
            .put("Typing (outline)", "COMMENTING_O")
            .put("Chat (fill)", "COMMENTS")
            .put("Chat (outline)", "COMMENTS_O")
            .put("Message (outline)", "COMMENT_O")
            .put("Compass", "COMPASS")
            .put("Compress", "COMPRESS")
            .put("Decompress", "EXPAND")
            .put("Connectdevelp", "CONNECTDEVELOP")
            .put("Contao", "CONTAO")
            .put("Copyright", "COPYRIGHT")
            .put("Creative commons", "CREATIVE_COMMONS")
            .put("Credit card (fill)", "CREDIT_CARD_ALT")
            .put("Credit card (outline)", "CREDIT_CARD")
            .put("Crop", "CROP")
            .put("Crosshair", "CROSSHAIRS")
            .put("Css3", "CSS3")
            .put("Cube", "CUBE")
            .put("Cubes", "CUBES")
            .put("Cutlery", "CUTLERY")
            .put("Dashcube", "DASHCUBE")
            .put("Database", "DATABASE")
            .put("Deaf", "DEAF")
            .put("Pixels", "DELICIOUS")
            .put("Monitor", "DESKTOP")
            .put("Diamond", "DIAMOND")
            .put("Digg", "DIGG")
            .put("mail (fill)", "ENVELOPE")
            .put("mail (outline)", "ENVELOPE_O")
            .put("mail (open fill)", "ENVELOPE_OPEN")
            .put("mail (open outline)", "ENVELOPE_OPEN_O")
            .put("Empire", "EMPIRE")
            .put("Eject", "EJECT")
            .put("Edge", "EDGE")
            .put("Drupal", "DRUPAL")
            .put("Dropbox", "DROPBOX")
            .put("Dribbble", "DRIBBBLE")
            .put("Download", "DOWNLOAD")
            .put("Dot", "DOT_CIRCLE_O")
            .put("Eraser", "ERASER")
            .put("E", "ETSY")
            .put("Eur", "EUR")
            .put("Exclamation", "EXCLAMATION")
            .put("Exchange", "EXCHANGE")
            .put("Exchange (circle)", "EXCLAMATION_CIRCLE")
            .put("Exchange (triangle)", "EXCLAMATION_TRIANGLE")
            .put("External", "EXTERNAL_LINK")
            .put("Eye", "EYE")
            .put("Facebook", "FACEBOOK")
            .put("Facebook (official)", "FACEBOOK_OFFICIAL")
            .put("Facebook (square)", "FACEBOOK_SQUARE")
            .put("Fast backward", "FAST_BACKWARD")
            .put("Fast forward", "FAST_FORWARD")
            .put("Fax", "FAX")
            .put("Female", "FEMALE")
            .put("Jet", "FIGHTER_JET")
            .put("File (fill)", "FILE")
            .put("File (outline)", "FILE_O")
            .put("Files (outline)", "FILES_O")
            .put("File (archive outline)", "FILE_ARCHIVE_O")
            .put("File (audio outline)", "FILE_AUDIO_O")
            .put("File (code outline)", "FILE_CODE_O")
            .put("File (excel outline)", "FILE_EXCEL_O")
            .put("File (image outline)", "FILE_IMAGE_O")
            .put("File (pdf outline)", "FILE_PDF_O")
            .put("File (powerpoint outline)", "FILE_POWERPOINT_O")
            .put("File (word outline)", "FILE_WORD_O")
            .put("File (video outline)", "FILE_VIDEO_O")
            .put("File (text fill)", "FILE_TEXT")
            .put("File (text outline)", "FILE_TEXT_O")
            .put("Film", "FILM")
            .put("Filter", "FILTER")
            .put("Fire", "FIRE")
            .put("Firefox", "FIREFOX")
            .put("Fire extinguisher", "FIRE_EXTINGUISHER")
            .put("Flag (fill)", "FLAG")
            .put("Flag (outline)", "FLAG_O")
            .put("Flag (checkered)", "FLAG_CHECKERED")
            .put("Flask", "FLASK")
            .put("Flicker", "FLICKR")
            .put("Floppy (outline)", "FLOPPY_O")
            .put("Folder (fill)", "FOLDER")
            .put("Folder (outline)", "FOLDER_O")
            .put("Folder (open fill)", "FOLDER_OPEN")
            .put("Folder (open outline)", "FOLDER_OPEN_O")
            .put("A", "FONT")
            .put("Fontawesome", "FONT_AWESOME")
            .put("Foursquare", "FOURSQUARE")
            .put("Free code camp", "FREE_CODE_CAMP")
            .put("Frown", "FROWN_O")
            .put("Football", "FUTBOL_O")
            .put("Gamepad", "GAMEPAD")
            .put("Gavel", "GAVEL")
            .put("GBP", "GBP")
            .put("Gift", "GIFT")
            .put("Github (latest)", "GITHUB")
            .put("Github (old)", "GITHUB_ALT")
            .put("Gitlab", "GITLAB")
            .put("Glass", "GLASS")
            .put("Glide", "GLIDE")
            .put("Glide G", "GLIDE_G")
            .put("Globe", "GLOBE")
            .put("Google", "GOOGLE")
            .put("Google plus", "GOOGLE_PLUS")
            .put("Google plus (official circle)", "GOOGLE_PLUS_OFFICIAL")
            .put("Google plus (official square)", "GOOGLE_PLUS_SQUARE")
            .put("Google wallet", "GOOGLE_WALLET")
            .put("Graduation", "GRADUATION_CAP")
            .put("Gravity", "GRAV")
            .put("Hacker news", "HACKER_NEWS")
            .put("Hand shake", "HANDSHAKE_O")
            .put("HDD", "HDD_O")
            .put("H", "HEADER")
            .put("Headphone", "HEADPHONES")
            .put("Heart (fill)", "HEART")
            .put("Heart (outline)", "HEART_O")
            .put("Heart beat", "HEARTBEAT")
            .put("History", "HISTORY")
            .put("Home", "HOME")
            .put("Hospital", "HOSPITAL_O")
            .put("Hourglass (fill)", "HOURGLASS")
            .put("Hourglass (outline)", "HOURGLASS_O")
            .put("Hourglass (start)", "HOURGLASS_START")
            .put("Hourglass (half)", "HOURGLASS_HALF")
            .put("Hourglass (end)", "HOURGLASS_END")
            .put("HTML5", "HTML5")
            .put("IMDB", "IMDB")
            .put("Inbox", "INBOX")
            .put("Indent", "INDENT")
            .put("Industry", "INDUSTRY")
            .put("Info", "INFO")
            .put("INR", "INR") // indian rubies
            .put("Instagram", "INSTAGRAM")
            .put("Explorer", "INTERNET_EXPLORER")
            .put("IOXHost", "IOXHOST")
            .put("JPY", "JPY")
            .put("Joomla", "JOOMLA")
            .put("Italic", "ITALIC")
            .put("Cursor", "I_CURSOR")
            .put("JSFiddle", "JSFIDDLE")
            .put("Key", "KEY")
            .put("Keyboard", "KEYBOARD_O")
            .put("KRW", "KRW")
            .put("Language", "LANGUAGE")
            .put("Laptop", "LAPTOP")
            .put("Last FM", "LASTFM")
            .put("Leaf", "LEAF")
            .put("Lemon", "LEMON_O")
            .put("Life ring", "LIFE_RING")
            .put("Lightbulb", "LIGHTBULB_O")
            .put("Chart (line)", "LINE_CHART")
            .put("Chain", "LINK")
            .put("LinkedIn", "LINKEDIN")
            .put("LinkedIn (square)", "LINKEDIN_SQUARE")
            .put("Linode", "LINODE")
            .put("Linux", "LINUX")
            .put("List", "LIST")
            .put("Location arrow", "LOCATION_ARROW")
            .put("Lock", "LOCK")
            .put("Stick", "MAGIC")
            .put("Magnet", "MAGNET")
            .put("Male", "MALE")
            .put("Map (fill)", "MAP")
            .put("Map (outline)", "MAP_O")
            .put("Map mark", "MAP_MARKER")
            .put("Map pin", "MAP_PIN")
            .put("Map signs", "MAP_SIGNS")
            .put("MAXCDN", "MAXCDN")
            .put("Medkit", "MEDKIT")
            .put("Micro chip", "MICROCHIP")
            .put("Microphone", "MICROPHONE")
            .put("Microphone (muted)", "MICROPHONE_SLASH")
            .put("Mobile", "MOBILE")
            .put("Money", "MONEY")
            .put("Moon", "MOON_O")
            .put("Motorcycle", "MOTORCYCLE")
            .put("Mouse pointer", "MOUSE_POINTER")
            .put("Music", "MUSIC")
            .put("Newspaper", "NEWSPAPER_O")
            .put("Group", "OBJECT_GROUP")
            .put("Ungroup", "OBJECT_UNGROUP")
            .put("Opera", "OPERA")
            .put("Optin monster", "OPTIN_MONSTER")
            .put("Paint brush", "PAINT_BRUSH")
            .put("Paperclip", "PAPERCLIP")
            .put("Paper plane (fill)", "PAPER_PLANE")
            .put("Paper plane (outline)", "PAPER_PLANE_O")
            .put("Reddit", "REDDIT_ALIEN")
            .put("Snapchat", "SNAPCHAT_GHOST")
            .put("Stack overflow", "STACK_OVERFLOW")
            .put("Stack exchange", "STACK_EXCHANGE")
            .put("star", "STAR")
            .put("Steam", "STEAM")
            .put("Subway", "SUBWAY")
            .put("Taxi", "TAXI")
            .put("Telegram", "TELEGRAM")
            .put("Themeisle", "THEMEISLE")
            .put("Thermometer (0/4)", "THERMOMETER_EMPTY")
            .put("Thermometer (1/4)", "THERMOMETER_QUARTER")
            .put("Thermometer (2/4)", "THERMOMETER_HALF")
            .put("Thermometer (3/4)", "THERMOMETER_THREE_QUARTERS")
            .put("Thermometer (4/4)", "THERMOMETER_FULL")
            .put("Thumb (down fill)", "THUMBS_DOWN")
            .put("Thumb (down outline)", "THUMBS_O_DOWN")
            .put("Thumb (up fill)", "THUMBS_UP")
            .put("Thumb (up outline)", "THUMBS_O_UP")
            .put("Thumb tack", "THUMB_TACK")
            .put("Ticket", "TICKET")
            .put("Drip", "TINT")
            .put("Trade mark", "TRADEMARK")
            .put("Train", "TRAIN")
            .put("Trash", "TRASH")
            .put("Tree", "TREE")
            .put("Trophy", "TROPHY")
            .put("Truck", "TRUCK")
            .put("TRY", "TRY")
            .put("Tumblr", "TUMBLR")
            .put("Twitch", "TWITCH")
            .put("Twitter", "TWITTER")
            .put("Umbrella", "UMBRELLA")
            .put("University", "UNIVERSITY")
            .put("Unlock (full)", "UNLOCK")
            .put("Unlock (half)", "UNLOCK_ALT")
            .put("upload", "UPLOAD")
            .put("USB", "USB")
            .put("USD", "USD")
            .put("User", "USER")
            .put("Users", "USERS")
            .put("Secret", "USER_SECRET")
            .put("Viacoin", "VIACOIN")
            .put("Video camera", "VIDEO_CAMERA")
            .put("Vine", "VINE")
            .put("Vimeo", "VIMEO")
            .put("Volume (off)", "VOLUME_OFF")
            .put("Volume (down)", "VOLUME_DOWN")
            .put("Volume (up)", "VOLUME_UP")
            .put("VK", "VK")
            .put("WEIXIN", "WEIXIN")
            .put("Whatsapp", "WHATSAPP")
            .put("Wifi", "WIFI")
            .put("Wikipedia", "WIKIPEDIA_W")
            .put("Windows", "WINDOWS")
            .put("Wordpress", "WORDPRESS")
            .put("Youtube", "YOUTUBE_PLAY")
            .put("Youtube (old outline)", "YOUTUBE")
            .put("Youtube (old fill)", "YOUTUBE_SQUARE")
//            .put("Wheelchair", "WHEELCHAIR")
            ;    
}
