#i/bin/bash

# -e : 명령 수행후 리턴값 체크, 0이 아닐 경우 자동 종료
# -x : 명령 실행전 명령 출력 (디버깅 용이함)
set -e -x

# 실행할 SQL문
sqls=(
  "schema.sql"
  "data-board.sql"
)

# 함수
execute() {
  path=$1
  shift
  sqls=("${@}")

  for sql in "${sqls[@]}"
  do
    # mysql --default-character-set=utf8 -uroot -proot 실행시 아래 에러 발생하여 비밀번호 환경변수 선언하여 스크립트 실행
    # mysql: [Warning] Using a password on the command line interface can be insecure.
    MYSQL_PWD="root" mysql --default-character-set=utf8 -uroot < "/$path/$sql"
  done
}

execute "sql" "${sqls[@]}"