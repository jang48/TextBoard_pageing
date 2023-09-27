package org.example;

import java.text.SimpleDateFormat;
import java.util.*;

public class TestBoard {
    ArrayList<Article> articles = new ArrayList<>();
    ArrayList<LoginInfo> login = new ArrayList<>();
    ArrayList<Comment> comments = new ArrayList<>();
    private Map<Integer, ArrayList<String>> likes = new HashMap<>();
    Date now = new Date();
    SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");
    String formatedNow = date.format(now);
    Scanner sc = new Scanner(System.in);
    int lastId = 4;
    String loginId = null;
    String loginNickname = null;
    String keyword = null;

    int currenpage = 1;


    public void start() {

//        Article a1 = new Article(1, "안녕하세요 반갑습니다. 자바 공부중이에요.", "자바 너무 재밌어요!!", formatedNow);
//        Article a2 = new Article(2, "자바 질문좀 할게요~", "자바 너무 재밌어요!!", formatedNow);
//        Article a3 = new Article(3, "정처기 따야되나요?", "자바 너무 재밌어요!!", formatedNow);
//
//        articles.add(a1);
//        articles.add(a2);
//        articles.add(a3);

        Page page = new Page(articles, 3, currenpage);

        while (true) {
            System.out.print("명령어 : ");
            String comment = sc.nextLine().trim();

            for(int i = 1; i <= 100; i++) {
                Article a1 = new Article(i, "안녕하세요" + i, "ㄴㄴㄴㄴㄴ", formatedNow);
                articles.add(a1);
            }
            if (comment.equals("add")) {
                articleadd();
            } else if (comment.equals("list")) {
                pageingList(page);
            } else if (comment.equals("update")) {
                System.out.print("수정할 게시물 번호 : ");
                int target = Integer.parseInt(sc.nextLine());

                System.out.print("새로운 제목 : ");
                String newtitle = sc.nextLine();
                System.out.print("새로운 내용 : ");
                String newcontent = sc.nextLine();

                articleUpdate(target, newtitle, newcontent);
                System.out.println("수정이 완료되었습니다.");

            } else if (comment.equals("detail")) {
                System.out.print("상세보기 할 게시물 번호를 입력해주세요 : ");
                int target = Integer.parseInt(sc.nextLine());
                if (target > 0 && target <= articles.size()) {
                    Article articled = articles.get(target - 1);
                    articled.setHit(articled.getHit()+1);
                    articleList(articled, target);
                    System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) : ");
                    int function = Integer.parseInt(sc.nextLine());
                    switch (function) {
                        case 1:
                            addcomment(target);
                            break;
                        case 2:
                            likecomment(target);
                            break;
                        case 3:
                            updatecomment(function, target, loginId, loginNickname);
                            break;
                        case 4:
                            updatecomment(function, target, loginId, loginNickname);
                            break;
                        case 5:
                            exitcomment();
                            break;
                        default:
                            System.out.println("잘못된 입력");
                            break;
                    }
                } else {
                    System.out.println("없는 게시물입니다.");
                }
            } else if (comment.equals("search")) {
                System.out.print("검색 키워드를 입력해주세요 : ");
                keyword = sc.nextLine();
                articleListtopage(keyword);
            } else if (comment.equals("delete")) {
                System.out.print("삭제할 게시물 번호 : ");
                int target = Integer.parseInt(sc.nextLine());
                if (0 <= target && target <= articles.size()) {
                    articles.remove(target - 1);
                    System.out.printf("%d번 게시물이 삭제되었습니다.\n", target);
                } else {
                    System.out.print("없는 게시물 번호입니다.\n");
                }
            } else if (comment.equals("signup")) {
                System.out.println("==== 회원 가입을 진행합니다 ====");
                System.out.print("아이디를 입력해주세요 : ");
                String joinid = sc.nextLine();
                System.out.print("비밀번호를 입력해주세요 : ");
                String joinpw = sc.nextLine();
                System.out.print("닉네임을 입력해주세요 : ");
                String joinname = sc.nextLine();
                LoginInfo newUser = new LoginInfo(joinid, joinpw, joinname);
                login.add(newUser);
                System.out.print("==== 회원가입이 완료되었습니다. ====\n");
            } else if (comment.equals("login")) {
                System.out.print("아이디 : ");
                String joinid = sc.nextLine();
                System.out.print("비밀번호 : ");
                String joinpw = sc.nextLine();
                login(joinid, joinpw);
            } else if (comment.equals("sort")) {
                System.out.print("정렬 대상을 선택해주세요. (1. 번호,  2. 조회수) : ");
                int target = Integer.parseInt(sc.nextLine());
                System.out.print("정렬 방법을 선택해주세요. (1. 오름차순,  2. 내림차순) : ");
                int sort = Integer.parseInt(sc.nextLine());
                sortArticle(target, sort);
            } else if (comment.equals("page")){
                // 현재 페이지의 데이터 출력
                pageingList(page);
                // Page 객체 생성
                while (true) {
                    // 페이지 이동 메뉴 출력
                    System.out.print("페이지 이동 (1. 이전, 2. 다음, 3. 특정 페이지, 4. 종료): ");
                    Scanner scanner = new Scanner(System.in);
                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            page.prevPage();
                            pageingList(page);
                            break;
                        case 2:
                            page.nextPage();
                            pageingList(page);
                            break;
                        case 3:
                            System.out.print("이동할 페이지 번호 입력: ");
                            int targetPage = Integer.parseInt(sc.nextLine());
                            int rePage = page.goToPage(targetPage);
                            if(rePage == 0){
                                System.out.println("없는 페이지 번호입니다.");
                            } else {
                                page = new Page(articles, 3,rePage);
                                pageingList(page);
                            }
                            break;
                        case 4:
                            System.out.println("프로그램 종료");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("잘못된 입력");
                            break;
                    }
                }
            } else if (comment.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
        }
    }
    public void articleListtopage(String keyword) {
        boolean check = false;

        System.out.println("===================");
        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            if (article.getTitle().contains(keyword)) {
                System.out.printf("번호 : %d\n", i + 1);
                System.out.printf("제목 : %s\n", article.getTitle());
                System.out.println("===================");
                check = true;
            }
        }
        if (!check) {
            System.out.println("검색 결과가 없습니다.");
            System.out.println("===================");
        }

    }

    public void articleList(Article article, int target) {
        System.out.println("========" + target + "번 게시물========");
        System.out.println("번호 : " + target);
        System.out.println("제목 : " + article.getTitle());
        System.out.println("내용 : " + article.getContent());
        System.out.println("등록날짜 : " + article.getDate());
        if (article.getJoinid() != null && !article.getJoinid().isEmpty() && article.getJoinid() != "") {
            System.out.println("작성자 : " + article.getJoinid());
        }
        ArrayList<String> likedBy = likes.get(target);
        if (likedBy != null && likedBy.contains(loginId)) {
            System.out.println("좋아요 : ♥ " + 1);
        } else {
            System.out.println("좋아요 : ♡ " + 0);
        }
        System.out.println("=========================");
        if (!comments.isEmpty()) {
            for (Comment comment : comments) {
                if (comment.getId() == target) {
                    System.out.printf("댓글 내용 : %s\n", comment.getComment());
                    System.out.printf("댓글 작성일 : %s\n", comment.getDate());
                    System.out.println("===================");
                }
            }
        }
    }

    public void articleUpdate(int id, String title, String content) {
        Article newArticle = new Article(id, title, content, formatedNow, loginId);
        articles.set(id - 1, newArticle);
    }

    public void addcomment(int Id) {
        sc.nextLine();
        System.out.print("댓글 내용 : ");
        String comment = sc.nextLine();
        Comment commentarticle = new Comment(Id, comment, formatedNow);
        comments.add(commentarticle);
        System.out.print("댓글이 성공적으로 등록되었습니다\n");
    }

    public void likecomment(int Id) {
        Article article5 = articles.get(Id - 1);
        if (loginId != null) {
            ArrayList<String> likedBy = likes.getOrDefault(Id, new ArrayList<>());

            if (likedBy.contains(loginId)) {
                likedBy.remove(loginId); // 이미 좋아요를 눌렀다면 해제
                article5.setLoveit(article5.getLoveit() - 1);
                System.out.println("해당 게시물의 좋아요를 해제합니다.");
            } else {
                likedBy.add(loginId); // 아니라면 좋아요 추가
                article5.setLoveit(article5.getLoveit() + 1);
                System.out.println("해당 게시물을 좋아합니다.");
            }
            likes.put(Id, likedBy);
            articleList(article5, Id);
        } else {
            System.out.println("로그인 후 사용 가능합니다.");
        }
    }

    public void updatecomment(int function, int target, String loginId, String loginNickname) {
        Article article6 = articles.get(target - 1);
        if (Objects.equals(article6.getJoinid(), loginId) && function == 3) {
            System.out.print("제목 : ");
            String retitle = sc.nextLine();
            System.out.print("내용 : ");
            String recontent = sc.nextLine();
            Article article2 = new Article(target, retitle, recontent, formatedNow, loginId);
            articles.set(target - 1, article2);
            articleList(article2, target);
        } else if (Objects.equals(article6.getJoinid(), loginId) && function == 4) {
            System.out.print("정말 게시물을 삭제하시겠습니까? (Y/N) : ");
            String answer = sc.nextLine().trim();
            if (answer.equals("Y") || answer.equals("y")) {
                articles.remove(target - 1);
                if (loginNickname != null) {
                    System.out.printf("%s님이 %d번 게시물을 삭제했습니다.\n", loginNickname, target);
                } else {
                    System.out.printf("%d번 게시물을 삭제했습니다.\n", target);
                }

            }
        } else {
            System.out.println("자신의 게시물만 수정/삭제 할 수 있습니다.");
        }
        sc.nextLine();

    }

    public void exitcomment() {
        System.out.println("상세보기 화면을 빠져나갑니다.");
    }

    public void login(String joinid, String joinpw) {
        boolean loggedIn = false;
        for (int i = 0; i < login.size(); i++) {
            LoginInfo lgoin = login.get(i);

            // joinid 또는 joinpw가 null이면 로그인 시도를 하지 않습니다.
            if (lgoin.getJoinid().equals(joinid) && lgoin.getJoinpw().equals(joinpw)) {
                loggedIn = true;
                loginId = lgoin.getJoinid(); // 사용자의 아이디 저장
                loginNickname = lgoin.getJoinname(); // 사용자의 닉네임 저장
                System.out.printf("%s님 환영합니다!\n", loginNickname);
                break; // 로그인 성공 시 루프를 종료합니다.
            }
        }
        if (!loggedIn) {
            System.out.println("비밀번호를 틀렸거나 잘못된 회원정보입니다.");
        }
    }

    public void articleadd() {
        if (loginId == null) {
            System.out.print("게시물 제목을 입력해주세요 : ");
            String title = sc.nextLine();
            System.out.print("게시물 내용을 입력해주세요 : ");
            String content = sc.nextLine();

            Article article = new Article(lastId, title, content, formatedNow);
            articles.add(article);
        } else if (!loginId.isEmpty()) {
            System.out.print("게시물 제목을 입력해주세요 : ");
            String title = sc.nextLine();
            System.out.print("게시물 내용을 입력해주세요 : ");
            String content = sc.nextLine();

            Article article = new Article(lastId, title, content, formatedNow, loginId);
            articles.add(article);
        }
        System.out.println("게시물이 등록되었습니다.");
    }

    public void sortArticle(int target, int sort) {
        if (target == 1 && sort == 1) {
            Collections.sort(articles, new ArticelComparator());
            sortArticleList();
        } else if (target == 1 && sort == 2) {
            Collections.sort(articles, new ArticelComparator().reversed());
            sortArticleList();
        } else if (target == 2 && sort == 1) {
            Collections.sort(articles, new ArticelComparator2());
            sortArticleList();
        } else if (target == 2 && sort == 2) {
            Collections.sort(articles, new ArticelComparator2().reversed());
            sortArticleList();
        }

    }

    public void sortArticleList() {
        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            System.out.printf("번호 : %d\n", article.getId());
            System.out.printf("제목 : %s\n", article.getTitle());
            if (article.getJoinid() != null) {
                System.out.printf("작성자 : %s\n", article.getJoinid());
            }
            System.out.printf("조회수 : %s\n", article.getHit());
            System.out.printf("좋아요 : %s\n", article.getLoveit());
            System.out.println("===================");
        }
    }
    public void pageingList(Page page){
        for(Article article : page.getCurrentPageItems()){
            System.out.println("번호 : " + article.getId());
            System.out.println("제목 : " + article.getTitle());
            if (article.getJoinid() != null && !article.getJoinid().isEmpty() && article.getJoinid() != "") {
                System.out.println("작성자 : " + article.getJoinid());
            }
            System.out.println("조회수 : " + article.getHit());
            System.out.println("좋아요 : " + article.getLoveit());
            System.out.println("================");
        }
        page.pageNumber(page.getCurrentPage(),page.getTotalPages());
    }
}

class ArticelComparator implements Comparator<Article> {
    @Override
    public int compare(Article A1, Article A2) {
        if (A1.getId() > A2.getId()) {
            return 1;
        } else if (A1.getId() < A2.getId()) {
            return -1;
        }
        return 0;
    }
}
class ArticelComparator2 implements Comparator<Article> {
    @Override
    public int compare(Article A1, Article A2) {
        if (A1.getHit() > A2.getHit()) {
            return 1;
        } else if (A1.getHit() < A2.getHit()) {
            return -1;
        }
        return 0;
    }
}