'use strict';

$(function(){

    addTagInput();
    addCareerInput();

});

function addTagInput() {

    $('.tagBtnAdd').click (function () {
        $('.tag_buttons').append (
            '<input type="text" name="tagContent"><input type="button" class="tagBtnRemove" value="Remove"><br>'
        );
        $('.tagBtnRemove').on('click', function () {
            $(this).prev().remove ();
            $(this).next ().remove ();
            $(this).remove ();
        });
    });
}

function addCareerInput() {

    $('.careerBtnAdd').click (function () {
        $('.career_buttons').append (
            '<input type="text" name="careerContent"><input type="button" class="careerBtnRemove" value="Remove"><br>'
        );
        $('.careerBtnRemove').on('click', function () {
            $(this).prev().remove ();
            $(this).next ().remove ();
            $(this).remove ();
        });
    });
}