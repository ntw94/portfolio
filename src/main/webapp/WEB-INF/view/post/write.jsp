<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/header.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="../../../resources/ckeditor5/build/ckeditor.js"></script>
</head>
<body>

<form action="/boards/${boardUri}/write" method="post">
    <table>
        <tr>
            <td>
                제목 <input type="text" name="postTitle" >
                말머리 선택:
                <select name="postCategory">
                        <option value="">일반</option>
                    <c:forEach var="list" items="${cateList}">
                        <option value="${list.categoryMenu}">${list.categoryMenu}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                작성자: <input type="text" name="postWriter" id= "postWriter" value="${member.memberId}" readonly/>
            </td>
        </tr>
        <tr>
            <td>
                <textarea style="height: 350px" name="postContent" id="editor">

                </textarea>

            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value ="만들기">
                <input type="button" onclick="location.href='/';" value ="취소">
            </td>
        </tr>
    </table>
</form>

<div name="postContent" id="postContent">

</div>

<script>
    class MyUploadAdapter {
        constructor( loader ) {
            this.loader = loader;
        }

        upload() {
            return this.loader.file
                .then( file => new Promise( ( resolve, reject ) => {
                    this._initRequest();
                    this._initListeners( resolve, reject, file );
                    this._sendRequest( file );
                } ) );
        }

        abort() {
            if ( this.xhr ) {
                this.xhr.abort();
            }
        }

        _initRequest() {
            const xhr = this.xhr = new XMLHttpRequest();
            xhr.open( 'POST', 'http://localhost:8080/api/image/upload', true );
            xhr.responseType = 'json';
        }

        // Initializes XMLHttpRequest listeners.
        _initListeners( resolve, reject, file ) {
            const xhr = this.xhr;
            const loader = this.loader;
            const genericErrorText = `Couldn't upload file: ${ file.name }.`;

            xhr.addEventListener( 'error', () => reject( genericErrorText ) );
            xhr.addEventListener( 'abort', () => reject() );
            xhr.addEventListener( 'load', () => {
                const response = xhr.response;

                if ( !response || response.error ) {
                    return reject( response && response.error ? response.error.message : genericErrorText );
                }

                resolve( {
                    default: response.url
                } );
            } );

            if ( xhr.upload ) {
                xhr.upload.addEventListener( 'progress', evt => {
                    if ( evt.lengthComputable ) {
                        loader.uploadTotal = evt.total;
                        loader.uploaded = evt.loaded;
                    }
                } );
            }
        }

        _sendRequest( file ) {
            const data = new FormData();
            data.append( 'upload', file );
            this.xhr.send( data );
        }
    }

    function MyCustomUploadAdapterPlugin( editor ) {
        editor.plugins.get( 'FileRepository' ).createUploadAdapter = ( loader ) => {
            // Configure the URL to the upload script in your back-end here!
            return new MyUploadAdapter( loader );
        };
    }
</script>
<script>
    ClassicEditor
        .create( document.querySelector( '#editor' ), {
            extraPlugins: [ MyCustomUploadAdapterPlugin],
            image:{
                resizeUnit:"%",
                resizeOptions: [{
                    name: 'resizeImage:original',
                    value: null
                },
                    {
                        name:'resizeImage:50',
                        value:'50'
                    },
                    {
                        name:'resizeImage:75',
                        value:'75'
                    }],
                toolbar: [
                    'imageTextAlternative',
                    'toggleImageCaption',
                    'imageStyle:inline',
                    'imageStyle:block',
                    'imageStyle:side',
                    'resizeImage'
                ]
            },


        })
        .then( editor => {
            window.editor = editor;
        }).catch( error => {
            console.log( error );
        } );
</script>

</body>
</html>