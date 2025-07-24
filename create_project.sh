#!/bin/bash

# ==============================================================================
# Master Script: Tự động hoá việc tạo Project TinhTX Player từ PROMPT.md
#
# HƯỚNG DẪN SỬ DỤNG:
# 1. Lưu file này với tên `create_project.sh` vào thư mục gốc của dự án.
# 2. Đặt file `PROMPT.md` của bạn vào cùng thư mục gốc.
# 3. CHỈNH SỬA BIẾN `AI_COMMAND` bên dưới để trỏ đến công cụ AI CLI bạn dùng.
# 4. Mở terminal, cấp quyền thực thi: `chmod +x create_project.sh`
# 5. Chạy lệnh duy nhất: `./create_project.sh`
# ==============================================================================

# --- CẤU HÌNH (VUI LÒNG CHỈNH SỬA) ---
# Thay thế "ai_cli_command --prompt" bằng lệnh thực tế của bạn.
# Ví dụ: "gh copilot suggest --" hoặc một Python script gọi Gemini API.
AI_COMMAND="gemini-cli"

# --- HÀM HỖ TRỢ ---
# Hàm để gọi AI và tạo file, kèm theo log
generate_file() {
    local file_path=$1
    local prompt_text=$2

    echo "   -> Đang tạo file: $file_path"
    # Tạo thư mục nếu chưa tồn tại
    mkdir -p "$(dirname "$file_path")"

    # Gọi AI và lưu kết quả vào file.
    # Dấu `>` sẽ ghi đè file, tạo file mới nếu chưa có.
    $AI_COMMAND "$prompt_text" > "$file_path"

    # Kiểm tra xem file có được tạo thành công không
    if [ -s "$file_path" ]; then
        echo "   ✔ Hoàn thành: $file_path"
    else
        echo "   ❌ LỖI: Không thể tạo nội dung cho $file_path. Vui lòng kiểm tra lại AI CLI."
        exit 1 # Dừng script nếu có lỗi
    fi
}

# --- BẮT ĐẦU THỰC THI ---
echo " BẮT ĐẦU TẠO DỰ ÁN TINHTX PLAYER TỪ PROMPT.MD "
echo "================================================="

# --- Phase 1: Foundation & Core Architecture ---
echo
echo "PHASE 1: Đang thiết lập Nền tảng & Kiến trúc Lõi..."

# Milestone 1.1: Project & Gradle Setup
echo "[Milestone 1.1] Đang tạo cấu trúc thư mục và các file Gradle..."
mkdir -p app/src/main/java/com/tinhtx/player \
         core/src/main/java/com/tinhtx/core \
         data/src/main/java/com/tinhtx/data \
         domain/src/main/java/com/tinhtx/domain \
         feature_home/src/main/java/com/tinhtx/feature/home \
         feature_player/src/main/java/com/tinhtx/feature/player \
         feature_collection/src/main/java/com/tinhtx/feature/collection \
         feature_settings/src/main/java/com/tinhtx/feature/settings

generate_file "gradle/libs.versions.toml" "Dựa vào file PROMPT.md, hãy tạo nội dung hoàn chỉnh cho file gradle/libs.versions.toml"
generate_file "settings.gradle.kts" "Dựa vào file PROMPT.md, hãy tạo nội dung hoàn chỉnh cho file settings.gradle.kts, bao gồm tất cả các module."
generate_file "build.gradle.kts" "Dựa vào file PROMPT.md, hãy tạo nội dung cho file build.gradle.kts ở thư mục gốc."
generate_file "app/build.gradle.kts" "Dựa vào file PROMPT.md, hãy tạo nội dung cho file build.gradle.kts của module app, với đầy đủ dependencies đến các module feature, domain, data, và core."
generate_file "core/build.gradle.kts" "Dựa vào file PROMPT.md, hãy tạo nội dung cho file build.gradle.kts của module core."
generate_file "data/build.gradle.kts" "Dựa vào file PROMPT.md, hãy tạo nội dung cho file build.gradle.kts của module data, với dependency đến module domain."
generate_file "domain/build.gradle.kts" "Dựa vào file PROMPT.md, hãy tạo nội dung cho file build.gradle.kts của module domain (module Kotlin/Java thuần túy)."
generate_file "feature_home/build.gradle.kts" "Dựa vào file PROMPT.md, hãy tạo nội dung cho file build.gradle.kts của module feature_home, với dependency đến module domain và core."
# (Lặp lại cho các module feature khác)
generate_file "feature_player/build.gradle.kts" "Dựa vào file PROMPT.md, hãy tạo nội dung cho file build.gradle.kts của module feature_player."
generate_file "feature_collection/build.gradle.kts" "Dựa vào file PROMPT.md, hãy tạo nội dung cho file build.gradle.kts của module feature_collection."
generate_file "feature_settings/build.gradle.kts" "Dựa vào file PROMPT.md, hãy tạo nội dung cho file build.gradle.kts của module feature_settings."


# --- Phase 2: Data & Domain Layers ---
echo
echo "PHASE 2: Đang xây dựng Lớp Dữ liệu & Nghiệp vụ..."

# Milestone 2.1: Domain Layer
echo "[Milestone 2.1] Đang triển khai Domain Layer..."
generate_file "domain/src/main/java/com/tinhtx/domain/model/MediaItem.kt" "Dựa vào PROMPT.md, tạo data class MediaItem trong module domain."
generate_file "domain/src/main/java/com/tinhtx/domain/repository/MediaRepository.kt" "Dựa vào PROMPT.md, tạo interface MediaRepository trong module domain."
generate_file "domain/src/main/java/com/tinhtx/domain/usecase/GetMediaUseCase.kt" "Dựa vào PROMPT.md, tạo class GetMediaUseCase trong module domain."

# Milestone 2.2: Data Layer
echo "[Milestone 2.2] Đang triển khai Data Layer..."
generate_file "data/src/main/java/com/tinhtx/data/local/database/AppDatabase.kt" "Dựa vào PROMPT.md, tạo lớp AppDatabase của Room trong module data."
generate_file "data/src/main/java/com/tinhtx/data/local/dao/MediaDao.kt" "Dựa vào PROMPT.md, tạo interface MediaDao của Room trong module data."
generate_file "data/src/main/java/com/tinhtx/data/repository/MediaRepositoryImpl.kt" "Dựa vào PROMPT.md, tạo lớp MediaRepositoryImpl trong module data, triển khai interface MediaRepository."
generate_file "data/src/main/java/com/tinhtx/data/local/scanner/MediaStoreScanner.kt" "Dựa vào PROMPT.md, tạo lớp MediaStoreScanner để quét media từ thiết bị."

# --- Phase 3 & 4 & 5: Presentation, Features... ---
# (Tiếp tục mở rộng kịch bản cho các Phase còn lại theo logic tương tự)
echo
echo "PHASE 3, 4, 5: Đang xây dựng Lớp Giao diện, Tính năng và Hoàn thiện..."
echo "[Milestone 3.2] Đang tạo màn hình Player..."
generate_file "feature_player/src/main/java/com/tinhtx/feature/player/PlayerScreen.kt" "Dựa vào PROMPT.md, tạo Composable cho màn hình PlayerScreen. Yêu cầu thiết kế đẹp mắt, hiện đại theo Material 3."
generate_file "feature_player/src/main/java/com/tinhtx/feature/player/PlayerViewModel.kt" "Dựa vào PROMPT.md, tạo PlayerViewModel cho PlayerScreen theo kiến trúc MVVM."

echo "[Milestone 4.1] Đang tạo màn hình Home..."
generate_file "feature_home/src/main/java/com/tinhtx/feature/home/HomeScreen.kt" "Dựa vào PROMPT.md, tạo Composable cho màn hình HomeScreen, hiển thị danh sách media trong LazyVerticalGrid."
generate_file "feature_home/src/main/java/com/tinhtx/feature/home/HomeViewModel.kt" "Dựa vào PROMPT.md, tạo HomeViewModel cho HomeScreen."

# ... Thêm các lệnh generate_file cho các màn hình và tính năng còn lại ...

echo
echo "================================================="
echo "✅ HOÀN TẤT! Dự án TinhTX Player đã được khởi tạo."
echo "Lưu ý: Hãy kiểm tra lại các file đã tạo và chạy lệnh './gradlew build' để xác thực."
