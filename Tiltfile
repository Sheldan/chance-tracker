allow_k8s_contexts('k8s-cluster')

load('ext://restart_process', 'docker_build_with_restart')
registry = 'harbor.sheldan.dev/drr/'

local_resource(
  'java-backend-compile',
  ' cd drr-backend && mvn install && ' +
  ' rm -rf executable/target/jar-staging && ' +
  ' unzip -o executable/target/drr-exec.jar -d executable/target/jar-staging && ' +
  ' rsync --delete --delete-excluded --inplace --checksum --exclude="*-SNAPSHOT.jar" -r executable/target/jar-staging/ executable/target/jar && ' +
  ' rm -rf executable/target/jar/snapshots && ' +
  ' mkdir executable/target/jar/snapshots && ' +
  ' rsync --delete --delete-excluded --inplace --checksum --include="*/" --include="*-SNAPSHOT.jar" --exclude="*" -r executable/target/jar-staging/BOOT-INF/lib/ executable/target/jar/snapshots',
  deps=['drr-backend/pom.xml'], auto_init=False, trigger_mode = TRIGGER_MODE_MANUAL)

docker_build_with_restart(
  registry + 'drr-backend',
  './drr-backend/executable/target/jar',
  entrypoint=['java', '-noverify', '-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005', '-cp', '.:./lib/*', 'dev.sheldan.gw2.drr.ChanceTrackerApplicationKt'],
  dockerfile='./drr-backend/executable/Dockerfile',
  live_update=[
    sync('./drr-backend/executable/target/jar/BOOT-INF/lib', '/app/lib'),
    sync('./drr-backend/executable/target/jar/META-INF', '/app/META-INF'),
    sync('./drr-backend/executable/target/jar/BOOT-INF/classes', '/app'),
    sync('./drr-backend/executable/target/jar/snapshots', '/app/lib')
  ],
)

docker_build(registry + 'drr-frontend', 'drr-frontend', dockerfile='drr-frontend/docker/Dockerfile')

local('cd tilt/drr-dev && helm dep up')
k8s_yaml(helm('tilt/drr-dev', values=['./../drr-environments/argocd/apps/drr/values/local/values.yaml']))

k8s_resource('backend', port_forwards='5005:5005')

local_resource('ui-build', 'export PUBLIC_URL=/drr && rm -rf drr-frontend/resources/drr/static && cd drr-ui && npm run build && cp -R build/* ../drr-frontend/resources/drr', auto_init=False, trigger_mode = TRIGGER_MODE_MANUAL)