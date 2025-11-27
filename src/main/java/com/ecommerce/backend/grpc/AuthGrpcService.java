package com.ecommerce.backend.grpc;

import com.ecommerce.backend.service.UserService;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
@RequiredArgsConstructor
public class AuthGrpcService extends AuthServiceGrpc.AuthServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(AuthGrpcService.class);

    private final UserService userService;

    @Override
    public void createUser(CreateUserRequest request,
                           StreamObserver<AuthServiceResponse> responseObserver) {
        handleServiceCall(() -> userService.createUser(request.getUsername(), request.getPassword()),
                responseObserver,
                "User created successfully");
    }

    @Override
    public void deleteUser(DeleteUserRequest request,
                           StreamObserver<AuthServiceResponse> responseObserver) {
        handleServiceCall(() -> userService.deleteUser(request.getUsername()),
                responseObserver,
                "User deleted successfully");
    }

    private void handleServiceCall(Runnable serviceAction,
                                   StreamObserver<AuthServiceResponse> responseObserver,
                                   String successMessage) {
        try {
            serviceAction.run();
            AuthServiceResponse response = AuthServiceResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage(successMessage)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (IllegalArgumentException e) {
            log.warn("Service call failed due to illegal argument: {}", e.getMessage());
            AuthServiceResponse response = AuthServiceResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage(e.getMessage())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("An unexpected error occurred during gRPC service call", e);
            AuthServiceResponse response = AuthServiceResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("An unexpected error occurred: " + e.getMessage())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}