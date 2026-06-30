## Overview
[![License: Unlicense](https://img.shields.io/badge/license-Unlicense-blue.svg)](http://unlicense.org/)
[![](https://img.shields.io/github/v/release/WB3IHY/QrBarcodeScannerReloaded)](https://github.com/WB3IHY/QrBarcodeScannerReloaded/releases/latest)

**QR & Barcode Scanner Reloaded** is a maintained fork of [wewewe718/QrAndBarcodeScanner](https://github.com/wewewe718/QrAndBarcodeScanner). It is ad-free, open-source, and uses the [ZXing][zxing] scanning library.

This fork was created because the original app had been inactive for several years and had accumulated a number of bugs and missing features. It is distributed under the same Unlicense terms as the original.

## Download

Download the latest APK from the [GitHub Releases](https://github.com/WB3IHY/QrBarcodeScannerReloaded/releases) page and sideload it onto your device.

F-Droid submission is planned.

## What's Different from the Original

- Fixed Android 11+ package visibility crash ("No app found") when opening contacts, market, or file picker
- Fixed WiFi QR connect on Android 10+ (suggestion + opens WiFi settings)
- Fixed WiFi QR parsing for Samsung devices that append trailing control characters
- Added WPA3 option to WiFi QR code creation
- Fixed vCard phone type recognition (VOICE qualifier no longer overwrites WORK/HOME/CELL)
- Fixed vCard address and website fields not transferring to Add to Contacts
- Fixed camera flip button added to scan screen for quick front/back switching
- Fixed flashlight not working on Sony Xperia and similar devices (Camera2 torch fallback)
- Fixed zoom slider resetting to zero after returning to scan screen
- Fixed scan-from-file failing on low-contrast or inverted images (multi-pass decode)
- Fixed malformed boarding pass barcodes crashing the app
- Added GS1 DataBar (RSS 14, RSS Expanded) to supported scan formats
- Added QR error correction level selector (L / M / Q / H) with display on scan result
- Removed stale Sentry/Internet permission entry from permissions screen
- Rebranded to avoid association with a similarly-named app that had a malware incident

## Contributing

Bug reports and pull requests are welcome via [GitHub Issues](https://github.com/WB3IHY/QrBarcodeScannerReloaded/issues).

## Supported Barcode Formats

### Read

* [AZTEC][aztec]
* [CODABAR][codabar]
* [CODE-39][code_39]
* [CODE-93][code_93]
* [CODE-128][code_128]
* [DATA MATRIX][data_matrix]
* [EAN-8][ean_8]
* [EAN-13][ean_13]
* [ITF][itf]
* [PDF417][pdf417]
* [QR CODE][qr_code]
* [RSS 14 (GS1 DataBar)][rss]
* [RSS EXPANDED (GS1 DataBar Expanded)][rss]
* [UPC-A][upc_a]
* [UPC-E][upc_e]
* [UPC-EAN EXTENSION][upc_ean]

### Create

* [AZTEC][aztec]
* [CODABAR][codabar]
* [CODE 39][code_39]
* [CODE 93][code_93]
* [CODE 128][code_128]
* [DATA MATRIX][data_matrix]
* [EAN-8][ean_8]
* [EAN-13][ean_13]
* [ITF][itf]
* [PDF417][pdf417]
* [QR CODE][qr_code]
* [UPC-A][upc_a]
* [UPC-E][upc_e]

[zxing]: https://github.com/zxing/zxing
[aztec]: https://en.wikipedia.org/wiki/Aztec_Code
[codabar]: https://en.wikipedia.org/wiki/Codabar
[code_39]: https://en.wikipedia.org/wiki/Code_39
[code_93]: https://en.wikipedia.org/wiki/Code_93
[code_128]: https://en.wikipedia.org/wiki/Code_128
[data_matrix]: https://en.wikipedia.org/wiki/Data_Matrix
[ean_8]: https://en.wikipedia.org/wiki/EAN-8
[ean_13]: https://en.wikipedia.org/wiki/International_Article_Number
[itf]: https://en.wikipedia.org/wiki/Interleaved_2_of_5
[pdf417]: https://en.wikipedia.org/wiki/PDF417
[qr_code]: https://en.wikipedia.org/wiki/QR_code
[rss]: https://en.wikipedia.org/wiki/GS1_DataBar
[upc_a]: https://en.wikipedia.org/wiki/Universal_Product_Code
[upc_e]: https://en.wikipedia.org/wiki/Universal_Product_Code#UPC-E
[upc_ean]: https://en.wikipedia.org/wiki/Universal_Product_Code#EAN-13
