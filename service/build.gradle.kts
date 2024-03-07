import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.jetbrains.kotlin.jvm") version "1.9.22"
    id("org.jetbrains.kotlin.plugin.spring") version "1.9.22"
    id("io.freefair.lombok") version "8.4"
    id("com.google.cloud.tools.jib") version "3.4.0"
}

val springBootProjects = listOf(
    project(":service:member"),
    project(":service:product"),
    project(":service:order"),
    project(":service:payment")
)

configure(springBootProjects) {
    apply(plugin = "kotlin")
    apply(plugin = "io.freefair.lombok")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "com.google.cloud.tools.jib")

    dependencies {
        implementation("com.github.dblys152:shared:1.0.7")

        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.springframework.boot:spring-boot-starter-logging:3.1.5")
        implementation("org.springframework.boot:spring-boot-starter-amqp:3.1.2")
        implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.0.3")
        implementation("io.github.openfeign:feign-okhttp:12.5")
        implementation("org.slf4j:slf4j-api:2.0.7")

        implementation("jakarta.validation:jakarta.validation-api:3.0.2")
        implementation("org.hibernate:hibernate-validator:7.0.5.Final")
        implementation("org.glassfish:jakarta.el:4.0.2")
        implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.2")
        implementation("com.github.f4b6a3:tsid-creator:5.2.6")
        implementation("org.apache.commons:commons-lang3:3.13.0")
        implementation("commons-validator:commons-validator:1.7")

        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
        runtimeOnly("com.h2database:h2")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "17"
            }
        }

        withType<JavaCompile> {
            sourceCompatibility = "17"
            targetCompatibility = "17"
        }

        withType<Test> {
            useJUnitPlatform()
        }
    }
}