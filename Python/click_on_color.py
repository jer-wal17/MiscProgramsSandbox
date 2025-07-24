from time import sleep
from datetime import datetime
import pyautogui as pag

download_button_x = 1460
download_button_y = 288

transfer_bar_x = 1039 * 2
transfer_bar_y = 346 * 2
transfer_bar_color = (75, 157, 248)

while True:
    now = datetime.now()
    print(now.strftime('%I:%M %p'), end='\t')
    if not pag.pixelMatchesColor(transfer_bar_x, transfer_bar_y, transfer_bar_color):
        print("Toggling download twice")
        pag.click(download_button_x, download_button_y)
        sleep(1)
        pag.click(download_button_x, download_button_y)
    else:
        print("Transfer still good")
    sleep(70)
