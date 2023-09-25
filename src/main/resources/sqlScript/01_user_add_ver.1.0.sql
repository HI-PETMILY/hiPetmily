----- sys 계정에서 -----

-- 기존 계정 삭제
DROP USER C##PETMILY CASCADE;

-- DB에 계정 추가
CREATE USER C##PETMILY IDENTIFIED BY PETMILY;
GRANT CONNECT, RESOURCE, CREATE VIEW TO C##PETMILY;
ALTER USER C##PETMILY DEFAULT TABLESPACE USERS QUOTA UNLIMITED ON USERS;