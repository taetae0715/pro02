SELECT * FROM user1;
-- 비밀번호 암호화
update user1 set pw='JZgpBi2Co8IxFb8OHdR7hktb8Jyk4+4k5U3gkYvghsL0Mre9F3sth49pnw9OUs6Oa/V4sw==' where id='jtj'; --0715
update user1 set pw='mI1zXrXiyjxgT2K74LaYDV6SPDqqSVsz4rKhpYJtO2QbhyjWllP7al3hyX3YaV0hen6x9g==' where id='jws'; --0104
update user1 set pw='6sMJkwH+FhEl2GE2oym5BIqWPX9CjcnKwz3jDpaW6tvxKHW4wr2IvxNTIghQR+ErX0au2w==' where id='ysr'; --1023
update user1 set pw='Z1u38Q+OsEVloDHEKeeGbAvl/WgmhavF+MUD2kFuN+wXOMDzScrVwsD4Yhg2uUuY3yUHqA==' where id='smj'; --0505
update user1 set pw='N8ySvu4rhuRW0Hp7RPTHTJ+mBa5cZxdSJuQnuCdgSBApGUlLtAeUwGjCZDZz3do6u1+Y/Q==' where id='kdh'; --0614
update user1 set pw='u8vbIuRVkNY1+WkYuxsDiiwXnFG0WIIBa8yWTD0nDw8v6CDcjXbzidgiZmjUszavKAqyYQ==' where id='admin'; --1234
update user1 set pw='nXvwt1jTv9pZ4x7fQQNc5bI8WAMDAk9spaCv6NlFpLXsZxX1Jc49NEudVhmn7eD6Yyg/Bw==' where id='kmm'; --4321
update notice set file1=null WHERE file1='data/null';

commit;
visited;
SELECT * FROM user1;
SELECT * FROM notice;