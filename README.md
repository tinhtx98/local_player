````markdown
# TinhTX Player

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/tinhtx/player)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue)](https://github.com/tinhtx/player/blob/main/LICENSE)
[![Kotlin Version](https://img.shields.io/badge/Kotlin-1.9.23-blueviolet)](https://kotlinlang.org)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-1.6.7-4285F4)](https://developer.android.com/jetpack/compose)

**Trình phát nhạc và video thế hệ mới, được xây dựng với sự tinh tế trong thiết kế và hiệu năng vượt trội. `TinhTX Player` mang lại trải nghiệm media cá nhân hóa, mượt mà và đầy cảm xúc.**

---

## ✨ Giới thiệu

`TinhTX Player` không chỉ là một ứng dụng phát media thông thường. Đây là một sản phẩm được tạo ra với triết lý **"local-first"**, tập trung vào trải nghiệm người dùng, thiết kế giao diện hiện đại và kiến trúc phần mềm bền vững. Dự án này là một minh chứng cho việc kết hợp giữa Jetpack Compose, kiến trúc module hóa và các nguyên tắc thiết kế của Material 3 để tạo ra một ứng dụng Android đẳng cấp thế giới.

## 📸 Ảnh chụp màn hình

*(Đây là nơi để trưng bày giao diện đẹp mắt của ứng dụng)*

| Màn hình chính | Trình phát nhạc | Trình phát video |
| :---: | :---: | :---: |
| [Ảnh chụp màn hình chính] | [Ảnh chụp màn hình phát nhạc] | [Ảnh chụp màn hình phát video] |

## 🚀 Tính năng nổi bật

### 🎵 Trải nghiệm Media
- **Hỗ trợ đa định dạng**: Phát mượt mà các định dạng phổ biến như MP3, FLAC, MP4, MKV,...
- **Chất lượng âm thanh cao**: Tích hợp Equalizer 10-band, Bass Boost, và Reverb.
- **Playback thông minh**: Hỗ trợ Crossfade, Gapless Playback và Sleep Timer.
- **Trình phát video mạnh mẽ**: Hỗ trợ phụ đề, chọn luồng âm thanh, điều khiển bằng cử chỉ và Picture-in-Picture (PiP).

### 🎨 Giao diện & Trải nghiệm người dùng (UI/UX)
- **Thiết kế Material 3**: Giao diện hiện đại, tận dụng Dynamic Color.
- **Hệ thống Theme theo Độ tuổi**: Giao diện tự động thay đổi (màu sắc, bo góc, font chữ) để phù hợp với 3 nhóm người dùng: Trẻ em, Thanh thiếu niên, và Người lớn.
- **Animation "Tự Nhiên Linh Hoạt"**: Mọi tương tác và chuyển động trong ứng dụng đều mượt mà, dựa trên các nguyên tắc vật lý tự nhiên.
- **Trạng thái giao diện hoàn chỉnh**: Thiết kế chi tiết cho các trạng thái loading, empty, và error.

### 📚 Quản lý Thư viện
- **Quét media thông minh**: Tự động quét và nhận diện media trên thiết bị.
- **Tổ chức khoa học**: Duyệt media theo Bài hát, Album, Nghệ sĩ, Thể loại, và Thư mục.
- **Quản lý Playlist mạnh mẽ**: Tạo, sửa, xóa playlist dễ dàng bằng giao diện kéo-thả.

## 🛠️ Kiến trúc & Công nghệ

Dự án này được xây dựng trên một nền tảng kỹ thuật hiện đại, tập trung vào khả năng mở rộng, bảo trì và hiệu năng.

### Ngăn xếp công nghệ (Tech Stack)
- **Ngôn ngữ**: [Kotlin](https://kotlinlang.org/) 100%
- **UI**: [Jetpack Compose](https://developer.android.com/jetpack/compose) & [Material 3](https://m3.material.io/)
- **Kiến trúc**: MVVM trên nền tảng Clean Architecture & Modularization
- **DI**: [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- **Bất đồng bộ**: [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) + [Flow](https://developer.android.com/kotlin/flow)
- **Database**: [Room](https://developer.android.com/training/data-storage/room)
- **Media Engine**: [ExoPlayer (Media3)](https://developer.android.com/guide/topics/media/exoplayer)
- **Build**: [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) + [Version Catalogs (TOML)](https://docs.gradle.org/current/userguide/platforms.html)

### Sơ đồ Kiến trúc Module
Dự án tuân thủ nghiêm ngặt kiến trúc module hóa với luồng phụ thuộc một chiều, đảm bảo sự độc lập và khả năng tái sử dụng.

```mermaid
graph TD;
    A[app] --> F1[feature_home];
    A --> F2[feature_player];
    A --> F3[feature_collection];
    A --> F4[feature_settings];
    A --> C[core];

    F1 --> D[domain];
    F2 --> D;
    F3 --> D;
    F4 --> D;

    F1 --> C;
    F2 --> C;
    F3 --> C;
    F4 --> C;

    DA[data] --> D;

    subgraph "Presentation Layer"
        A
        F1
        F2
        F3
        F4
    end

    subgraph "Domain Layer"
        D
    end

    subgraph "Data Layer"
        DA
    end

    subgraph "Common"
        C
    end
````

## 📦 Cấu trúc Dự án

```
TinhTXPlayer/
├── app/                # Module ứng dụng chính, kết nối các feature
├── core/               # Module chứa code dùng chung (utils, navigation, di)
├── data/               # Module xử lý dữ liệu (Room, MediaStore, API)
├── domain/             # Module chứa logic nghiệp vụ (models, usecases, repositories)
├── feature_home/       # Module cho tính năng màn hình chính
├── feature_player/     # Module cho tính năng trình phát
├── feature_collection/ # Module cho tính năng bộ sưu tập
└── feature_settings/   # Module cho tính năng cài đặt
```

## 🚀 Bắt đầu

Để bắt đầu với dự án này, bạn chỉ cần thực hiện các bước sau:

1.  **Clone a repository:**
    ```bash
    git clone [https://github.com/tinhtx/player.git](https://github.com/tinhtx/player.git)
    ```
2.  **Mở bằng Android Studio:**
    Mở dự án bằng Android Studio (phiên bản Iguana trở lên được khuyến nghị).
3.  **Đồng bộ Gradle:**
    Android Studio sẽ tự động đồng bộ và tải về các thư viện cần thiết dựa trên file `libs.versions.toml`.
4.  **Chạy ứng dụng:**
    Nhấn nút `Run 'app'` để build và cài đặt ứng dụng lên máy ảo hoặc thiết bị thật.

## 🤝 Đóng góp

Chúng tôi luôn chào đón các đóng góp để làm cho `TinhTX Player` trở nên tốt hơn\! Nếu bạn có ý tưởng hoặc muốn sửa lỗi, vui lòng làm theo các bước sau:

1.  **Fork** a repository.
2.  Tạo một branch mới (`git checkout -b feature/AmazingFeature`).
3.  Commit các thay đổi của bạn (`git commit -m 'Add some AmazingFeature'`).
4.  Push lên branch (`git push origin feature/AmazingFeature`).
5.  Mở một **Pull Request**.

## 📄 Giấy phép

Dự án này được cấp phép dưới Giấy phép Apache 2.0 - xem file [LICENSE](https://www.google.com/search?q=LICENSE) để biết chi tiết.

-----

Made with ❤️ by TinhTX.

```
```
