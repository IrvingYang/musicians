/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here.
	// For the complete reference:
	// http://docs.ckeditor.com/#!/api/CKEDITOR.config

	// The toolbar groups arrangement, optimized for two toolbar rows.
	config.toolbarGroups = [
		{ name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },
		{ name: 'editing',     groups: [ 'find', 'selection', 'spellchecker' ] },
		{ name: 'links' },
		{ name: 'insert' },
		{ name: 'forms' },
		{ name: 'tools' },
		{ name: 'document',	   groups: [ 'mode', 'document', 'doctools' ] },
		{ name: 'others' },
		'/',
		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
		{ name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ] },
		{ name: 'styles' },
		{ name: 'colors' },
		{ name: 'about' }
	];
//	
	config.toolbar = [
	                       ['Source','-','Save','NewPage','Preview','-','Templates'],
	                       ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'],
	                       ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
	                       ['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField'],
	                       '/',
	                       ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
	                        ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
	                        ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
	                        ['Link','Unlink','Anchor'],
	                       ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
	                       '/',
	                        ['Styles','Format','Font','FontSize'],
	                        ['TextColor','BGColor']
	                    ]; 

	// Remove some buttons, provided by the standard plugins, which we don't
	// need to have in the Standard(s) toolbar.
	config.removeButtons = 'Underline,Subscript,Superscript';
	config.width = 800;

	config.height = 600;
	// Se the most common block elements.
	config.format_tags = 'p;h1;h2;h3;pre';

	// Make dialogs simpler.
	config.removeDialogTabs = 'image:advanced;link:advanced';
	
	//加入ckfinder集成
	config.filebrowserBrowseUrl = '/ckfinder/ckfinder.html';
    config.filebrowserImageBrowseUrl = '/ckfinder/ckfinder.html?type=Images';
    config.filebrowserFlashBrowseUrl = '/ckfinder/ckfinder.html?type=Flash';
    config.filebrowserUploadUrl = '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files';
    config.filebrowserImageUploadUrl = '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images';
    config.filebrowserFlashUploadUrl = '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash';

    config.enterMode = CKEDITOR.ENTER_BR;// CKEDITOR.ENTER_P;CKEDITOR.ENTER_DIV
    config.format_p = { element: 'p', attributes: { 'style': 'text-indent:24.0pt;line-height:150%' } };
    config.font_style = { element   : 'span', styles   : { 'font-size' : '14.0pt;', 'line-height' : '200%;', 'font-family' : '宋体;' } };
    config.font_defaultLabel = '宋体';
    config.fontSize_defaultLabel = '12px';
    config.tabSpaces = 2;

   
};
