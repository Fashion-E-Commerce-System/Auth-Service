package com.ecommerce.backend.service;

import com.ecommerce.backend.grpc.CreateUserRequest;
import com.ecommerce.backend.grpc.DeleteUserRequest;
import com.ecommerce.backend.grpc.UserEventServiceGrpc;
import com.ecommerce.backend.grpc.UserResponse;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class UserEventGrpcService extends UserEventServiceGrpc.UserEventServiceImplBase {

    private final UserService userService;

    @Override
    public void createUser(CreateUserRequest request, StreamObserver<UserResponse> responseObserver) {
        try {
            userService.createUser(request.getName(), request.getPassword());
            UserResponse response = UserResponse.newBuilder()
                    .setStatus("SUCCESS")
                    .setMessage("User created successfully")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (IllegalArgumentException e) {
            UserResponse response = UserResponse.newBuilder()
                    .setStatus("ERROR")
                    .setMessage(e.getMessage())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void deleteUser(DeleteUserRequest request, StreamObserver<UserResponse> responseObserver) {
        try {
            userService.deleteUser(request.getName());
            UserResponse response = UserResponse.newBuilder()
                    .setStatus("SUCCESS")
                    .setMessage("User deleted successfully")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (IllegalArgumentException e) {
            UserResponse response = UserResponse.newBuilder()
                    .setStatus("ERROR")
                    .setMessage(e.getMessage())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
