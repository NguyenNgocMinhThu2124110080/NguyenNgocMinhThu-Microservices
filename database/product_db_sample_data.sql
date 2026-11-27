USE product_db;

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE products;
TRUNCATE TABLE categories;
TRUNCATE TABLE brands;
SET FOREIGN_KEY_CHECKS = 1;

-- Dữ liệu bảng categories
INSERT INTO categories (name, description) VALUES
('Thực phẩm khô', 'Mì, gạo, bún, miến, cháo ăn liền'),
('Đồ uống', 'Nước ngọt, nước suối, trà, cà phê'),
('Sữa & Sản phẩm sữa', 'Sữa tươi, sữa đặc, sữa chua, bơ, phô mai'),
('Gia vị & Nước chấm', 'Nước mắm, tương, muối, đường, bột ngọt'),
('Bánh kẹo & Snack', 'Bánh quy, kẹo, snack các loại');

-- Dữ liệu bảng brands
INSERT INTO brands (name, description) VALUES
('Vinamilk', 'Công ty Cổ phần Sữa Việt Nam'),
('TH True Milk', 'Tập đoàn TH'),
('Acecook', 'Acecook Việt Nam'),
('Masan', 'Tập đoàn Masan'),
('Coca-Cola', 'The Coca-Cola Company'),
('Nestle', 'Nestle Việt Nam'),
('Bibica', 'Công ty CP Bibica');

-- Dữ liệu bảng products
INSERT INTO products (name, price, quantity, description, image_url, status, category_id, brand_id) VALUES
('Mì Hảo Hảo Tôm Chua Cay 75g', 4500.00, 200, 'Mì ăn liền vị tôm chua cay Acecook', NULL, true, 1, 3),
('Mì Hảo Hảo Sa Tế Hành 75g', 4500.00, 150, 'Mì ăn liền vị sa tế hành Acecook', NULL, true, 1, 3),
('Phở Bò Ăn Liền Đệ Nhất 65g', 8000.00, 120, 'Phở bò ăn liền hương vị truyền thống Acecook', NULL, true, 1, 3),
('Thùng 24 lon Nước Ngọt Coca-Cola 320ml', 215000.00, 80, 'Nước ngọt có gas Coca-Cola thùng 24 lon', NULL, true, 2, 5),
('Trà Xanh Không Độ 500ml', 10000.00, 300, 'Trà xanh đóng chai không đường', NULL, true, 2, 4),
('Nước suối Lavie 500ml', 5000.00, 500, 'Nước khoáng thiên nhiên Lavie', NULL, true, 2, 6),
('Cà phê sữa đá Nescafe lon 180ml', 12000.00, 180, 'Cà phê sữa đá đóng lon tiện lợi Nestle', NULL, true, 2, 6),
('Thùng 48 Hộp Sữa Tươi Vinamilk 180ml', 385000.00, 50, 'Sữa tươi tiệt trùng ít đường Vinamilk', NULL, true, 3, 1),
('Sữa Tươi TH True Milk 1 lít', 32000.00, 100, 'Sữa tươi thanh trùng nguyên chất TH', NULL, true, 3, 2),
('Sữa Đặc Có Đường Ngôi Sao Phương Nam 380g', 25000.00, 90, 'Sữa đặc có đường Vinamilk', NULL, true, 3, 1),
('Sữa Chua Vinamilk Có Đường 100g x 4 hũ', 28000.00, 80, 'Sữa chua ăn Vinamilk lốc 4 hũ', NULL, true, 3, 1),
('Nước Mắm Chin-su 40 độ đạm 500ml', 35000.00, 150, 'Nước mắm Chin-su đặm đà hương vị Nam Bộ', NULL, true, 4, 4),
('Tương Ớt Chin-su 250g', 18000.00, 200, 'Tương ớt đậm đặc Chin-su', NULL, true, 4, 4),
('Hạt Nêm Chinsu Ngọt Tôm Thịt 400g', 32000.00, 140, 'Hạt nêm từ tôm và thịt thăn xương ống', NULL, true, 4, 4),
('Bánh Quy Hương Bơ Bibica 384g', 45000.00, 120, 'Bánh quy giòn hương bơ thơm ngon Bibica', NULL, true, 5, 7);
