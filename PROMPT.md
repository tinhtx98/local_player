# Prompt Mô Tả Chi Tiết Dự Án TinhTX Player (Dành cho AI Senior Android & UI/UX Designer)

## **I. Bối cảnh & Kỳ vọng**

Bạn là một **senior Android developer (Kotlin, Compose, MVVM, Hilt, Room, ExoPlayer)** đồng thời là **UI/UX designer chuyên nghiệp**. Mục tiêu: Xây dựng dự án **TinhTX Player** – local-first music & video player đẳng cấp thế giới, tối ưu code kiến trúc và xuất sắc về mặt UI/UX. Yêu cầu của dự án là chi tiết hóa kiến trúc lớp, toàn bộ class chính và HỆ THỐNG ANIMATION "TỰ NHIÊN LINH HOẠT" mang bản sắc riêng.

## **II. Project Scope & Architecture**

### 1. **Tech Stack**
- **Ngôn ngữ:** Kotlin 100%
- **UI:** Jetpack Compose (Material 3)
- **Kiến trúc:** MVVM + Clean Arch, chia module nghiêm ngặt (app, core, data, domain, feature_*)
- **DI:** Hilt
- **DB:** Room, DataStore
- **Playback:** ExoPlayer 2.19.1+
- **Image:** Coil
- **Async:** Coroutines, StateFlow/SharedFlow

### 2. **Module Structure**

| Module              | Chức năng chính                                            |
|---------------------|-----------------------------------------------------------|
| app                 | Entry, DI, main theme, navigation                         |
| core                | Common: navigation, theme, util, resource                 |
| domain              | Models, repository interface, usecases(no Android deps)   |
| data                | Entity, DAO, DB, repository impl, MediaStore, DataStore   |
| feature_home        | Home UI, ViewModel, business logic                        |
| feature_player      | Music & video player UI, player service, notification     |
| feature_collection  | Collection/library UI, playlist, tabs                     |
| feature_settings    | Settings UI, theme, privacy, language                     |
| feature_pip         | Picture-in-Picture logic                                  |
| feature_equalizer   | Equalizer, effects UI & logic                             |

## **III. Animation System: “Tự Nhiên Linh Hoạt”**

### 1. **Motif Animation Đặc Trưng**

- **Giọt Nước (Drop Collapse):** Khi thu nhỏ mini-player về góc nhỏ – sử dụng spring animation cho cảm giác chất lỏng.
- **Nở Hoa (Bloom Expand):** Từ mini-player mở rộng ra full player, mô phỏng hoa nở với scale + opacity ripple, phối hợp stagger.
- **Sóng Nước (Ripple):** Feedback khi tap/long-press button hoặc thẻ media, dùng Compose ripple + custom opacity/scale ripple layer.
- **Lá Rơi (Falling Leaves):** Hiệu ứng chuyển màn hình hoặc mở tab, lá ảo rơi chéo với cubic Bézier motion path, opacity giảm dần.
- **Mặt Nước (Surface Parallax):** Parallax nhẹ khi cuộn lyrics hoặc album art, phối hợp translationY + alpha.

### 2. **Technical Implementation**

- **SpringSpec:** Dùng cho mọi animation scale, translation, opacity để đạt cảm giác vật lý (bouncy hoặc damped).
    - **Ví dụ:**  album art xoay, expand/collapse player, chuyển tab.
- **Easing Functions:** FastOutSlowInEasing cho các hiệu ứng chuyển đổi nhịp nhàng, kết hợp LinearOutSlowIn khi cần delay hoặc finish mạnh.
- **AnimationScope & Compose:** Mỗi motif nên gói thành hàm `@Composable` riêng, sử dụng `animate*AsState`, `Transition`, hoặc `AnimatedVisibility` tuỳ mục đích.
- **Duration Guidelines:** Vi mô 200–400ms, macro (screen, collapse) ≤ 600ms. Tùy biến độ nảy, thời gian theo theme độ tuổi.
- **Accessibility:** Luôn lắng nghe prefers-reduced-motion; giảm intensity nếu người dùng yêu cầu.

### 3. **Tuỳ biến theo độ tuổi (3-Tier)**
| Tier   | Độ nảy | Opacity | Tốc độ   | Complex motion |
|--------|--------|---------|----------|---------------|
| Child  | Rất nhiều| 100%  | Nhanh   | Đa lớp        |
| Teen   | Vừa    | 80%     | Trung bình | 2 lớp         |
| Adult  | Nhẹ nhàng| 60%  | Chậm    | Tối giản      |

## **IV. Toàn bộ Các Class Chủ Đạo (và Vai Trò) – Mẫu Tối Ưu Android Clean Architecture**

### 1. Lớp hệ thống

- **TinhTXPlayerApplication : Application**  
  Cài đặt Hilt, khởi tạo các thành phần toàn cục.
- **MainActivity : ComponentActivity**  
  Chứa setContent, điều hướng, status bar, dynamic theme.

### 2. **DI Module (Hilt)**
- **AppModule, DatabaseModule, MediaModule, NetworkModule**  
  `@Module` cung cấp instance database, repository, player, image loader.

### 3. **Theme System**

- **TinhTXTheme.kt**:
  Xử lý Material 3 colorScheme, typography, shape, dynamic color, theme theo độ tuổi.
- **Color.kt, Shape.kt, Typography.kt**:  
  Định nghĩa từng biến theme tùy biến cho 3 group tuổi.

### 4. **Navigation**

- **AppNavigation.kt, Screen.kt**:  
  Định danh route, ánh xạ với composable, animated transitions giữa screen (dùng AnimatedNavHost).

### 5. **UI Layer – Presentation**

- **HomeScreen, MusicPlayerScreen, VideoPlayerScreen, CollectionScreen, SettingsScreen**  
  Các class `@Composable` đại diện cho màn hình lớn, gắn `ViewModel` (StateFlow).
- **AppStateHolder**:  
  Quản lý trạng thái toàn cục theme, playback, userPref.
- **Common Components**:
    - `AnimatedIconButton.kt` (icon vật động)
    - `SkeletonLoader.kt` (loading state)
    - `AnimatedEmptyState.kt` (empty/error với illustration)
    - `RippleButton.kt` (sóng nước)
- **Animation Composables**:
    - `DropCollapseAnimatedBox, BloomExpandBox, RippleEffectLayer, FallingLeafLayer, SurfaceParallaxBox`
- **Feedback**:
    - `HapticFeedbackWrapper.kt`, trigger rung có kiểm soát

### 6. **ViewModels**

- Mỗi màn hình có 1 ViewModel:
    - **HomeViewModel, PlayerViewModel, CollectionViewModel, SettingsViewModel**
    - Sử dụng state holder, trigger animation state, handle event

### 7. **Domain Layer**

- **Model**
    - class Song, Album, Artist, Playlist, Video, UserSettings, ThemeMode
- **Repository Interface**
    - MediaRepository, PlaylistRepository, SettingsRepository...
- **UseCase**
    - GetSongListUseCase, PlaySongUseCase, ToggleThemeModeUseCase...

### 8. **Data Layer**

- **Room Entity + DAO**
    - SongEntity, AlbumEntity, ArtistEntity, PlaylistEntity, UserSettingsEntity
    - SongDao, AlbumDao, ...
- **AppDatabase**
    - Chứa truy vấn và version hóa database
- **RepositoryImpl**
    - Triển khai các repository kết nối DAO + MediaStore
- **DataStore**
    - Quản lý preferences: theme, language, age group...

### 9. **Media Layer**

- **ExoPlayerManager**: Quản lý playback, queue, kết nối UI qua callback.
- **PlaybackService**: Điều khiển nền, foreground service, media session.
- **MediaNotificationManager**: Notification custom, control bar.

### 10. **Feature Layer**
- **PictureInPictureManager**
- **EqualizerManager**

## **V. Yêu cầu về Material 3 & Icon**

- Toàn bộ component sử dụng Material 3, import:  
  `androidx.compose.material3.*`  
  Icon:  
  `androidx.compose.material.icons.Icons.Filled.*`  
  NavigationBar, FloatingActionButton, NavigationBarItem... đều dùng icon Material 3 chuẩn.

## **VI. Yêu cầu bổ sung**

- Code phải sạch, dễ đọc, chia module, class rõ ràng.
- Comment giải thích logic, đặc biệt phần animation motif tự code riêng, không chỉ mặc định ripple.
- Mọi class quan trọng phải thể hiện rõ dependency, injected qua Hilt hoặc constructor.
- Sẵn sàng test/javadoc cho cả developer và designer khác đọc hiểu.
- File cấu hình, build script đều tuân thủ chuẩn Kotlin DSL.

## **VII. Output mong muốn khi nhập prompt này cho AI**

AI sẽ:
- Trình bày chi tiết cấu trúc project, phân tích từng class chính, mô tả từng animation motif với các thông số kỹ thuật rõ ràng (duration, spring, easing, motion theo age group...).
- Đề xuất/thể hiện các class hoặc component mẫu phù hợp từng màn hình, chuẩn Clean Architecture.
- Luôn tham chiếu code thực tế kèm giải thích cho các section đặc biệt về animation, theme hoặc hệ thống UI.
- Luôn đưa ra giải pháp tối ưu cả về trải nghiệm, performance và maintainability như một senior thực thụ.

*Prompt này dành cho AI có năng lực "đặt vào vị trí kỹ sư Android kỳ cựu và designer chuyên nghiệp", để tạo output chất lượng, thuyết phục và đúng định hướng sản phẩm mong muốn.*

Sources
