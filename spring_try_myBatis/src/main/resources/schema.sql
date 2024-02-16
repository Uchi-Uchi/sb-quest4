drop table if exists todo;

CREATE TABLE todo (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    details VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL
);

INSERT INTO todo (id, title, status, details, created_at, updated_at)
VALUES
  ('1', '買い物', '着手', '野菜を買う', NOW(), NOW()),
  ('2', '洗濯', '完了', 'タオルを洗う', NOW(), NOW()),
  ('3', '料理', '未着手', '肉じゃがを作る', NOW(), NOW()),
  ('4', '掃除', '未着手', 'お風呂を掃除する', NOW(), NOW()),
  ('5', '勉強', '未着手', 'Javaを勉強する', NOW(), NOW());