package com.ecommerce.backend.grpc;

import com.ecommerce.backend.service.UserService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class AuthGrpcService extends AuthServiceGrpc.AuthServiceImplBase {

    private final UserService userService;

    @Override
    public void createUser(AuthProto.CreateUserRequest request,
                           StreamObserver<AuthProto.AuthServiceResponse> responseObserver) {
        try {
            userService.createUser(request.getUsername(), request.getPassword());

            AuthProto.AuthServiceResponse response = AuthProto.AuthServiceResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("User created successfully")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (IllegalArgumentException e) {
            AuthProto.AuthServiceResponse response = AuthProto.AuthServiceResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage(e.getMessage())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void deleteUser(AuthProto.DeleteUserRequest request,
                           StreamObserver<AuthProto.AuthServiceResponse> responseObserver) {
        try {
            userService.deleteUser(request.getUsername());

            AuthProto.AuthServiceResponse response = AuthProto.AuthServiceResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("User deleted successfully")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (IllegalArgumentException e) {
            AuthProto.AuthServiceResponse response = AuthProto.AuthServiceResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage(e.getMessage())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}