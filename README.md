# prometheus-sample
프로메테우스 샘플

## window wsl Ubuntu 설정 내용

### ✅ yml 파일 생성
global:
  scrape_interval: 5s

scrape_configs:
  - job_name: 'spring-boot-app'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['host.docker.internal:8080']  # 로컬에서 도커 실행 시

### ✅ 도커로 실행
docker run -d -p 9090:9090 \
  -v $(pwd)/prometheus.yml:/etc/prometheus/prometheus.yml \
  prom/prometheus

### ✅ Grafana 설정
docker run -d -p 3000:3000 grafana/grafana

### ✅ Grafana 대시보드 확인
1. ``` http://localhost:3000 ``` 접속
2. 기본 로그인 : ``` admin / admin ```
