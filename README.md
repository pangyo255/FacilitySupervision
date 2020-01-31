# FacilitySupervision
설비감독 기술조사 프로젝트

## MD파일 문서 작성에 필요한 Markdown문법 작성 중   

강조하고 **싶은** 부분   
기울임체 하고 ***싶은*** 부분   
취소하고 싶은 부분 ~~취소선 지정~~ 부분   


1. 일번
2. 이번
3. 삼번

* 빨강
   * 녹색
     * 파랑

This is a normal paragraph: 
    This is a code block. 
end code block.

* 코드 작성시 pre code 태그 사용

<pre><code>public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "테스트야 commit테스트", Toast.LENGTH_SHORT).show();
    }
}</code></pre>

* 코드블럭코드("```") 을 이용하는 방법
```
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "테스트야 commit테스트", Toast.LENGTH_SHORT).show();
    }
```  
