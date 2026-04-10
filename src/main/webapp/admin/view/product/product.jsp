<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container-fluid p-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h3 class="fw-bold">Danh sách sản phẩm</h3>
        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addProductModal">
            <i class="bi bi-plus-circle me-2"></i>Thêm sản phẩm mới
        </button>
    </div>

    <div class="card border-0 shadow-sm">
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-hover align-middle">
                    <thead class="table-light">
                    <tr>
                        <th>ID</th>
                        <th>Ảnh</th>
                        <th>Tên sản phẩm</th>
                        <th>Danh mục</th>
                        <th>Giá bán</th>
                        <th>Kho</th>
                        <th>Trạng thái</th>
                        <th class="text-end">Thao tác</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="p" items="${listProduct}">
                        <tr>
                            <td>#${p.id}</td>
                            <td>
                                <img src="${p.image}" alt="${p.name}"
                                     class="rounded" style="width: 50px; height: 50px; object-fit: cover;">
                            </td>
                            <td>
                                <div class="fw-bold">${p.name}</div>
                                <small class="text-muted text-truncate d-block" style="max-width: 200px;">
                                        ${p.description}
                                </small>
                            </td>
                            <td>
                                <span class="badge bg-info text-dark">${p.categoryName}</span>
                            </td>
                            <td>
                                <div class="text-danger fw-bold">
                                    <fmt:formatNumber value="${p.price}" type="currency" currencySymbol="đ"/>
                                </div>
                                <c:if test="${p.discountPrice > 0}">
                                    <small class="text-decoration-line-through text-muted">
                                        <fmt:formatNumber value="${p.discountPrice}" type="currency" currencySymbol="đ"/>
                                    </small>
                                </c:if>
                            </td>
                            <td>${p.stockQuantity}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${p.status == 1}">
                                        <span class="badge bg-success">Đang bán</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-secondary">Tạm ẩn</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="text-end">
                                <a href="product?action=edit&id=${p.id}" class="btn btn-sm btn-outline-primary me-1">
                                    <i class="bi bi-pencil"></i>
                                </a>
                                <button class="btn btn-sm btn-outline-danger" onclick="confirmDelete(${p.id})">
                                    <i class="bi bi-trash"></i>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty listProduct}">
                        <tr>
                            <td colspan="8" class="text-center py-4 text-muted">Không có sản pkẩm nào được tìm thấy.</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>