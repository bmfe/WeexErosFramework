// { "framework": "Vue" }

/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
          
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
          
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId])
/******/ 			return installedModules[moduleId].exports;
          
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			exports: {},
/******/ 			id: moduleId,
/******/ 			loaded: false
/******/ 		};
          
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
          
/******/ 		// Flag the module as loaded
/******/ 		module.loaded = true;
          
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
          
          
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
          
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
          
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "/Users/yangmingzhe/Work/weex-eros/fe/dist/js/";
          
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("'use strict';\n\n__webpack_require__(1);\n\n__webpack_require__(83);\n\n__webpack_require__(84);\n\n__webpack_require__(119);\n\n__webpack_require__(120);\n\n__webpack_require__(121);\n\n__webpack_require__(122);\n\n__webpack_require__(123);\n\n__webpack_require__(124);\n\n__webpack_require__(125);\n\n__webpack_require__(127);\n\n__webpack_require__(128);\n\n/*****************\n ** WEBPACK FOOTER\n ** ./src/js/config/base.js\n ** module id = 0\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./src/js/config/base.js?");
           
/***/ },
/* 1 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("'use strict';\n\nvar _promise = __webpack_require__(2);\n\nvar _promise2 = _interopRequireDefault(_promise);\n\nvar _create = __webpack_require__(72);\n\nvar _create2 = _interopRequireDefault(_create);\n\nvar _isFunction = __webpack_require__(75);\n\nvar _isFunction2 = _interopRequireDefault(_isFunction);\n\nfunction _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }\n\nvar appConfig = weex.requireModule('bmAppConfig'); /**\n                                                   * @Author: songqi\n                                                   * @Date:   2017-01-11\n                                                   * @Last modified by:   songqi\n                                                   * @Last modified time: 2017-05-08\n                                                   */\n\nvar Font = (0, _create2.default)(null);\n\nFont.install = function (Vue, options) {\n    Vue.prototype.$font = {\n        changeFontSize: function changeFontSize(options) {\n            var _this = this;\n\n            return new _promise2.default(function (resolve, reject) {\n                appConfig.changeFontSize({\n                    fontSize: options.fontSize || 'NORM'\n                }, function (resData) {\n                    if ((0, _isFunction2.default)(options.callback)) {\n                        options.callback.call(_this, data);\n                    }\n                    if (resData && resData.resCode == 0) {\n                        resolve(resData);\n                    } else {\n                        reject(resData);\n                    }\n                });\n            });\n        },\n        getFontSize: function getFontSize(callback) {\n            var _this2 = this;\n\n            return new _promise2.default(function (resolve, reject) {\n                appConfig.getFontSize(function (resData) {\n                    if ((0, _isFunction2.default)(callback)) {\n                        callback.call(_this2, resData);\n                    }\n                    if (resData && resData.resCode == 0) {\n                        resolve(resData);\n                    } else {\n                        reject(resData);\n                    }\n                });\n            });\n        }\n    };\n};\n\nVue.use(Font);\n\n/*****************\n ** WEBPACK FOOTER\n ** ./src/js/widget/font.js\n ** module id = 1\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./src/js/widget/font.js?");
           
/***/ },
/* 2 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("module.exports = { \"default\": __webpack_require__(3), __esModule: true };\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_babel-runtime@6.25.0@babel-runtime/core-js/promise.js\n ** module id = 2\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_babel-runtime@6.25.0@babel-runtime/core-js/promise.js?");
           
/***/ },
/* 3 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("__webpack_require__(4);\n__webpack_require__(5);\n__webpack_require__(49);\n__webpack_require__(53);\n__webpack_require__(70);\n__webpack_require__(71);\nmodule.exports = __webpack_require__(13).Promise;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/fn/promise.js\n ** module id = 3\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/fn/promise.js?");
           
/***/ },
/* 4 */
/***/ function(module, exports) {
           
           eval("\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/es6.object.to-string.js\n ** module id = 4\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/es6.object.to-string.js?");
           
/***/ },
/* 5 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("'use strict';\nvar $at = __webpack_require__(6)(true);\n\n// 21.1.3.27 String.prototype[@@iterator]()\n__webpack_require__(9)(String, 'String', function (iterated) {\n  this._t = String(iterated); // target\n  this._i = 0;                // next index\n// 21.1.5.2.1 %StringIteratorPrototype%.next()\n}, function () {\n  var O = this._t;\n  var index = this._i;\n  var point;\n  if (index >= O.length) return { value: undefined, done: true };\n  point = $at(O, index);\n  this._i += point.length;\n  return { value: point, done: false };\n});\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/es6.string.iterator.js\n ** module id = 5\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/es6.string.iterator.js?");
           
/***/ },
/* 6 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var toInteger = __webpack_require__(7);\nvar defined = __webpack_require__(8);\n// true  -> String#at\n// false -> String#codePointAt\nmodule.exports = function (TO_STRING) {\n  return function (that, pos) {\n    var s = String(defined(that));\n    var i = toInteger(pos);\n    var l = s.length;\n    var a, b;\n    if (i < 0 || i >= l) return TO_STRING ? '' : undefined;\n    a = s.charCodeAt(i);\n    return a < 0xd800 || a > 0xdbff || i + 1 === l || (b = s.charCodeAt(i + 1)) < 0xdc00 || b > 0xdfff\n      ? TO_STRING ? s.charAt(i) : a\n      : TO_STRING ? s.slice(i, i + 2) : (a - 0xd800 << 10) + (b - 0xdc00) + 0x10000;\n  };\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_string-at.js\n ** module id = 6\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_string-at.js?");
           
/***/ },
/* 7 */
/***/ function(module, exports) {
           
           eval("// 7.1.4 ToInteger\nvar ceil = Math.ceil;\nvar floor = Math.floor;\nmodule.exports = function (it) {\n  return isNaN(it = +it) ? 0 : (it > 0 ? floor : ceil)(it);\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_to-integer.js\n ** module id = 7\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_to-integer.js?");
           
/***/ },
/* 8 */
/***/ function(module, exports) {
           
           eval("// 7.2.1 RequireObjectCoercible(argument)\nmodule.exports = function (it) {\n  if (it == undefined) throw TypeError(\"Can't call method on  \" + it);\n  return it;\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_defined.js\n ** module id = 8\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_defined.js?");
           
/***/ },
/* 9 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("'use strict';\nvar LIBRARY = __webpack_require__(10);\nvar $export = __webpack_require__(11);\nvar redefine = __webpack_require__(26);\nvar hide = __webpack_require__(16);\nvar has = __webpack_require__(27);\nvar Iterators = __webpack_require__(28);\nvar $iterCreate = __webpack_require__(29);\nvar setToStringTag = __webpack_require__(45);\nvar getPrototypeOf = __webpack_require__(47);\nvar ITERATOR = __webpack_require__(46)('iterator');\nvar BUGGY = !([].keys && 'next' in [].keys()); // Safari has buggy iterators w/o `next`\nvar FF_ITERATOR = '@@iterator';\nvar KEYS = 'keys';\nvar VALUES = 'values';\n\nvar returnThis = function () { return this; };\n\nmodule.exports = function (Base, NAME, Constructor, next, DEFAULT, IS_SET, FORCED) {\n  $iterCreate(Constructor, NAME, next);\n  var getMethod = function (kind) {\n    if (!BUGGY && kind in proto) return proto[kind];\n    switch (kind) {\n      case KEYS: return function keys() { return new Constructor(this, kind); };\n      case VALUES: return function values() { return new Constructor(this, kind); };\n    } return function entries() { return new Constructor(this, kind); };\n  };\n  var TAG = NAME + ' Iterator';\n  var DEF_VALUES = DEFAULT == VALUES;\n  var VALUES_BUG = false;\n  var proto = Base.prototype;\n  var $native = proto[ITERATOR] || proto[FF_ITERATOR] || DEFAULT && proto[DEFAULT];\n  var $default = $native || getMethod(DEFAULT);\n  var $entries = DEFAULT ? !DEF_VALUES ? $default : getMethod('entries') : undefined;\n  var $anyNative = NAME == 'Array' ? proto.entries || $native : $native;\n  var methods, key, IteratorPrototype;\n  // Fix native\n  if ($anyNative) {\n    IteratorPrototype = getPrototypeOf($anyNative.call(new Base()));\n    if (IteratorPrototype !== Object.prototype && IteratorPrototype.next) {\n      // Set @@toStringTag to native iterators\n      setToStringTag(IteratorPrototype, TAG, true);\n      // fix for some old engines\n      if (!LIBRARY && !has(IteratorPrototype, ITERATOR)) hide(IteratorPrototype, ITERATOR, returnThis);\n    }\n  }\n  // fix Array#{values, @@iterator}.name in V8 / FF\n  if (DEF_VALUES && $native && $native.name !== VALUES) {\n    VALUES_BUG = true;\n    $default = function values() { return $native.call(this); };\n  }\n  // Define iterator\n  if ((!LIBRARY || FORCED) && (BUGGY || VALUES_BUG || !proto[ITERATOR])) {\n    hide(proto, ITERATOR, $default);\n  }\n  // Plug for library\n  Iterators[NAME] = $default;\n  Iterators[TAG] = returnThis;\n  if (DEFAULT) {\n    methods = {\n      values: DEF_VALUES ? $default : getMethod(VALUES),\n      keys: IS_SET ? $default : getMethod(KEYS),\n      entries: $entries\n    };\n    if (FORCED) for (key in methods) {\n      if (!(key in proto)) redefine(proto, key, methods[key]);\n    } else $export($export.P + $export.F * (BUGGY || VALUES_BUG), NAME, methods);\n  }\n  return methods;\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_iter-define.js\n ** module id = 9\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_iter-define.js?");
           
/***/ },
/* 10 */
/***/ function(module, exports) {
           
           eval("module.exports = true;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_library.js\n ** module id = 10\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_library.js?");
           
/***/ },
/* 11 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var global = __webpack_require__(12);\nvar core = __webpack_require__(13);\nvar ctx = __webpack_require__(14);\nvar hide = __webpack_require__(16);\nvar PROTOTYPE = 'prototype';\n\nvar $export = function (type, name, source) {\n  var IS_FORCED = type & $export.F;\n  var IS_GLOBAL = type & $export.G;\n  var IS_STATIC = type & $export.S;\n  var IS_PROTO = type & $export.P;\n  var IS_BIND = type & $export.B;\n  var IS_WRAP = type & $export.W;\n  var exports = IS_GLOBAL ? core : core[name] || (core[name] = {});\n  var expProto = exports[PROTOTYPE];\n  var target = IS_GLOBAL ? global : IS_STATIC ? global[name] : (global[name] || {})[PROTOTYPE];\n  var key, own, out;\n  if (IS_GLOBAL) source = name;\n  for (key in source) {\n    // contains in native\n    own = !IS_FORCED && target && target[key] !== undefined;\n    if (own && key in exports) continue;\n    // export native or passed\n    out = own ? target[key] : source[key];\n    // prevent global pollution for namespaces\n    exports[key] = IS_GLOBAL && typeof target[key] != 'function' ? source[key]\n    // bind timers to global for call from export context\n    : IS_BIND && own ? ctx(out, global)\n    // wrap global constructors for prevent change them in library\n    : IS_WRAP && target[key] == out ? (function (C) {\n      var F = function (a, b, c) {\n        if (this instanceof C) {\n          switch (arguments.length) {\n            case 0: return new C();\n            case 1: return new C(a);\n            case 2: return new C(a, b);\n          } return new C(a, b, c);\n        } return C.apply(this, arguments);\n      };\n      F[PROTOTYPE] = C[PROTOTYPE];\n      return F;\n    // make static versions for prototype methods\n    })(out) : IS_PROTO && typeof out == 'function' ? ctx(Function.call, out) : out;\n    // export proto methods to core.%CONSTRUCTOR%.methods.%NAME%\n    if (IS_PROTO) {\n      (exports.virtual || (exports.virtual = {}))[key] = out;\n      // export proto methods to core.%CONSTRUCTOR%.prototype.%NAME%\n      if (type & $export.R && expProto && !expProto[key]) hide(expProto, key, out);\n    }\n  }\n};\n// type bitmap\n$export.F = 1;   // forced\n$export.G = 2;   // global\n$export.S = 4;   // static\n$export.P = 8;   // proto\n$export.B = 16;  // bind\n$export.W = 32;  // wrap\n$export.U = 64;  // safe\n$export.R = 128; // real proto method for `library`\nmodule.exports = $export;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_export.js\n ** module id = 11\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_export.js?");
           
/***/ },
/* 12 */
/***/ function(module, exports) {
           
           eval("// https://github.com/zloirock/core-js/issues/86#issuecomment-115759028\nvar global = module.exports = typeof window != 'undefined' && window.Math == Math\n  ? window : typeof self != 'undefined' && self.Math == Math ? self\n  // eslint-disable-next-line no-new-func\n  : Function('return this')();\nif (typeof __g == 'number') __g = global; // eslint-disable-line no-undef\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_global.js\n ** module id = 12\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_global.js?");
           
/***/ },
/* 13 */
/***/ function(module, exports) {
           
           eval("var core = module.exports = { version: '2.5.0' };\nif (typeof __e == 'number') __e = core; // eslint-disable-line no-undef\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_core.js\n ** module id = 13\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_core.js?");
           
/***/ },
/* 14 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("// optional / simple context binding\nvar aFunction = __webpack_require__(15);\nmodule.exports = function (fn, that, length) {\n  aFunction(fn);\n  if (that === undefined) return fn;\n  switch (length) {\n    case 1: return function (a) {\n      return fn.call(that, a);\n    };\n    case 2: return function (a, b) {\n      return fn.call(that, a, b);\n    };\n    case 3: return function (a, b, c) {\n      return fn.call(that, a, b, c);\n    };\n  }\n  return function (/* ...args */) {\n    return fn.apply(that, arguments);\n  };\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_ctx.js\n ** module id = 14\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_ctx.js?");
           
/***/ },
/* 15 */
/***/ function(module, exports) {
           
           eval("module.exports = function (it) {\n  if (typeof it != 'function') throw TypeError(it + ' is not a function!');\n  return it;\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_a-function.js\n ** module id = 15\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_a-function.js?");
           
/***/ },
/* 16 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var dP = __webpack_require__(17);\nvar createDesc = __webpack_require__(25);\nmodule.exports = __webpack_require__(21) ? function (object, key, value) {\n  return dP.f(object, key, createDesc(1, value));\n} : function (object, key, value) {\n  object[key] = value;\n  return object;\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_hide.js\n ** module id = 16\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_hide.js?");
           
/***/ },
/* 17 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var anObject = __webpack_require__(18);\nvar IE8_DOM_DEFINE = __webpack_require__(20);\nvar toPrimitive = __webpack_require__(24);\nvar dP = Object.defineProperty;\n\nexports.f = __webpack_require__(21) ? Object.defineProperty : function defineProperty(O, P, Attributes) {\n  anObject(O);\n  P = toPrimitive(P, true);\n  anObject(Attributes);\n  if (IE8_DOM_DEFINE) try {\n    return dP(O, P, Attributes);\n  } catch (e) { /* empty */ }\n  if ('get' in Attributes || 'set' in Attributes) throw TypeError('Accessors not supported!');\n  if ('value' in Attributes) O[P] = Attributes.value;\n  return O;\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_object-dp.js\n ** module id = 17\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_object-dp.js?");
           
/***/ },
/* 18 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var isObject = __webpack_require__(19);\nmodule.exports = function (it) {\n  if (!isObject(it)) throw TypeError(it + ' is not an object!');\n  return it;\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_an-object.js\n ** module id = 18\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_an-object.js?");
           
/***/ },
/* 19 */
/***/ function(module, exports) {
           
           eval("module.exports = function (it) {\n  return typeof it === 'object' ? it !== null : typeof it === 'function';\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_is-object.js\n ** module id = 19\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_is-object.js?");
           
/***/ },
/* 20 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("module.exports = !__webpack_require__(21) && !__webpack_require__(22)(function () {\n  return Object.defineProperty(__webpack_require__(23)('div'), 'a', { get: function () { return 7; } }).a != 7;\n});\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_ie8-dom-define.js\n ** module id = 20\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_ie8-dom-define.js?");
           
/***/ },
/* 21 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("// Thank's IE8 for his funny defineProperty\nmodule.exports = !__webpack_require__(22)(function () {\n  return Object.defineProperty({}, 'a', { get: function () { return 7; } }).a != 7;\n});\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_descriptors.js\n ** module id = 21\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_descriptors.js?");
           
/***/ },
/* 22 */
/***/ function(module, exports) {
           
           eval("module.exports = function (exec) {\n  try {\n    return !!exec();\n  } catch (e) {\n    return true;\n  }\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_fails.js\n ** module id = 22\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_fails.js?");
           
/***/ },
/* 23 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var isObject = __webpack_require__(19);\nvar document = __webpack_require__(12).document;\n// typeof document.createElement is 'object' in old IE\nvar is = isObject(document) && isObject(document.createElement);\nmodule.exports = function (it) {\n  return is ? document.createElement(it) : {};\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_dom-create.js\n ** module id = 23\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_dom-create.js?");
           
/***/ },
/* 24 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("// 7.1.1 ToPrimitive(input [, PreferredType])\nvar isObject = __webpack_require__(19);\n// instead of the ES6 spec version, we didn't implement @@toPrimitive case\n// and the second argument - flag - preferred type is a string\nmodule.exports = function (it, S) {\n  if (!isObject(it)) return it;\n  var fn, val;\n  if (S && typeof (fn = it.toString) == 'function' && !isObject(val = fn.call(it))) return val;\n  if (typeof (fn = it.valueOf) == 'function' && !isObject(val = fn.call(it))) return val;\n  if (!S && typeof (fn = it.toString) == 'function' && !isObject(val = fn.call(it))) return val;\n  throw TypeError(\"Can't convert object to primitive value\");\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_to-primitive.js\n ** module id = 24\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_to-primitive.js?");
           
/***/ },
/* 25 */
/***/ function(module, exports) {
           
           eval("module.exports = function (bitmap, value) {\n  return {\n    enumerable: !(bitmap & 1),\n    configurable: !(bitmap & 2),\n    writable: !(bitmap & 4),\n    value: value\n  };\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_property-desc.js\n ** module id = 25\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_property-desc.js?");
           
/***/ },
/* 26 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("module.exports = __webpack_require__(16);\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_redefine.js\n ** module id = 26\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_redefine.js?");
           
/***/ },
/* 27 */
/***/ function(module, exports) {
           
           eval("var hasOwnProperty = {}.hasOwnProperty;\nmodule.exports = function (it, key) {\n  return hasOwnProperty.call(it, key);\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_has.js\n ** module id = 27\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_has.js?");
           
/***/ },
/* 28 */
/***/ function(module, exports) {
           
           eval("module.exports = {};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_iterators.js\n ** module id = 28\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_iterators.js?");
           
/***/ },
/* 29 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("'use strict';\nvar create = __webpack_require__(30);\nvar descriptor = __webpack_require__(25);\nvar setToStringTag = __webpack_require__(45);\nvar IteratorPrototype = {};\n\n// 25.1.2.1.1 %IteratorPrototype%[@@iterator]()\n__webpack_require__(16)(IteratorPrototype, __webpack_require__(46)('iterator'), function () { return this; });\n\nmodule.exports = function (Constructor, NAME, next) {\n  Constructor.prototype = create(IteratorPrototype, { next: descriptor(1, next) });\n  setToStringTag(Constructor, NAME + ' Iterator');\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_iter-create.js\n ** module id = 29\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_iter-create.js?");
           
/***/ },
/* 30 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("// 19.1.2.2 / 15.2.3.5 Object.create(O [, Properties])\nvar anObject = __webpack_require__(18);\nvar dPs = __webpack_require__(31);\nvar enumBugKeys = __webpack_require__(43);\nvar IE_PROTO = __webpack_require__(40)('IE_PROTO');\nvar Empty = function () { /* empty */ };\nvar PROTOTYPE = 'prototype';\n\n// Create object with fake `null` prototype: use iframe Object with cleared prototype\nvar createDict = function () {\n  // Thrash, waste and sodomy: IE GC bug\n  var iframe = __webpack_require__(23)('iframe');\n  var i = enumBugKeys.length;\n  var lt = '<';\n  var gt = '>';\n  var iframeDocument;\n  iframe.style.display = 'none';\n  __webpack_require__(44).appendChild(iframe);\n  iframe.src = 'javascript:'; // eslint-disable-line no-script-url\n  // createDict = iframe.contentWindow.Object;\n  // html.removeChild(iframe);\n  iframeDocument = iframe.contentWindow.document;\n  iframeDocument.open();\n  iframeDocument.write(lt + 'script' + gt + 'document.F=Object' + lt + '/script' + gt);\n  iframeDocument.close();\n  createDict = iframeDocument.F;\n  while (i--) delete createDict[PROTOTYPE][enumBugKeys[i]];\n  return createDict();\n};\n\nmodule.exports = Object.create || function create(O, Properties) {\n  var result;\n  if (O !== null) {\n    Empty[PROTOTYPE] = anObject(O);\n    result = new Empty();\n    Empty[PROTOTYPE] = null;\n    // add \"__proto__\" for Object.getPrototypeOf polyfill\n    result[IE_PROTO] = O;\n  } else result = createDict();\n  return Properties === undefined ? result : dPs(result, Properties);\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_object-create.js\n ** module id = 30\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_object-create.js?");
           
/***/ },
/* 31 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var dP = __webpack_require__(17);\nvar anObject = __webpack_require__(18);\nvar getKeys = __webpack_require__(32);\n\nmodule.exports = __webpack_require__(21) ? Object.defineProperties : function defineProperties(O, Properties) {\n  anObject(O);\n  var keys = getKeys(Properties);\n  var length = keys.length;\n  var i = 0;\n  var P;\n  while (length > i) dP.f(O, P = keys[i++], Properties[P]);\n  return O;\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_object-dps.js\n ** module id = 31\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_object-dps.js?");
           
/***/ },
/* 32 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("// 19.1.2.14 / 15.2.3.14 Object.keys(O)\nvar $keys = __webpack_require__(33);\nvar enumBugKeys = __webpack_require__(43);\n\nmodule.exports = Object.keys || function keys(O) {\n  return $keys(O, enumBugKeys);\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_object-keys.js\n ** module id = 32\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_object-keys.js?");
           
/***/ },
/* 33 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var has = __webpack_require__(27);\nvar toIObject = __webpack_require__(34);\nvar arrayIndexOf = __webpack_require__(37)(false);\nvar IE_PROTO = __webpack_require__(40)('IE_PROTO');\n\nmodule.exports = function (object, names) {\n  var O = toIObject(object);\n  var i = 0;\n  var result = [];\n  var key;\n  for (key in O) if (key != IE_PROTO) has(O, key) && result.push(key);\n  // Don't enum bug & hidden keys\n  while (names.length > i) if (has(O, key = names[i++])) {\n    ~arrayIndexOf(result, key) || result.push(key);\n  }\n  return result;\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_object-keys-internal.js\n ** module id = 33\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_object-keys-internal.js?");
           
/***/ },
/* 34 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("// to indexed object, toObject with fallback for non-array-like ES3 strings\nvar IObject = __webpack_require__(35);\nvar defined = __webpack_require__(8);\nmodule.exports = function (it) {\n  return IObject(defined(it));\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_to-iobject.js\n ** module id = 34\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_to-iobject.js?");
           
/***/ },
/* 35 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("// fallback for non-array-like ES3 and non-enumerable old V8 strings\nvar cof = __webpack_require__(36);\n// eslint-disable-next-line no-prototype-builtins\nmodule.exports = Object('z').propertyIsEnumerable(0) ? Object : function (it) {\n  return cof(it) == 'String' ? it.split('') : Object(it);\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_iobject.js\n ** module id = 35\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_iobject.js?");
           
/***/ },
/* 36 */
/***/ function(module, exports) {
           
           eval("var toString = {}.toString;\n\nmodule.exports = function (it) {\n  return toString.call(it).slice(8, -1);\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_cof.js\n ** module id = 36\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_cof.js?");
           
/***/ },
/* 37 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("// false -> Array#indexOf\n// true  -> Array#includes\nvar toIObject = __webpack_require__(34);\nvar toLength = __webpack_require__(38);\nvar toAbsoluteIndex = __webpack_require__(39);\nmodule.exports = function (IS_INCLUDES) {\n  return function ($this, el, fromIndex) {\n    var O = toIObject($this);\n    var length = toLength(O.length);\n    var index = toAbsoluteIndex(fromIndex, length);\n    var value;\n    // Array#includes uses SameValueZero equality algorithm\n    // eslint-disable-next-line no-self-compare\n    if (IS_INCLUDES && el != el) while (length > index) {\n      value = O[index++];\n      // eslint-disable-next-line no-self-compare\n      if (value != value) return true;\n    // Array#indexOf ignores holes, Array#includes - not\n    } else for (;length > index; index++) if (IS_INCLUDES || index in O) {\n      if (O[index] === el) return IS_INCLUDES || index || 0;\n    } return !IS_INCLUDES && -1;\n  };\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_array-includes.js\n ** module id = 37\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_array-includes.js?");
           
/***/ },
/* 38 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("// 7.1.15 ToLength\nvar toInteger = __webpack_require__(7);\nvar min = Math.min;\nmodule.exports = function (it) {\n  return it > 0 ? min(toInteger(it), 0x1fffffffffffff) : 0; // pow(2, 53) - 1 == 9007199254740991\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_to-length.js\n ** module id = 38\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_to-length.js?");
           
/***/ },
/* 39 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var toInteger = __webpack_require__(7);\nvar max = Math.max;\nvar min = Math.min;\nmodule.exports = function (index, length) {\n  index = toInteger(index);\n  return index < 0 ? max(index + length, 0) : min(index, length);\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_to-absolute-index.js\n ** module id = 39\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_to-absolute-index.js?");
           
/***/ },
/* 40 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var shared = __webpack_require__(41)('keys');\nvar uid = __webpack_require__(42);\nmodule.exports = function (key) {\n  return shared[key] || (shared[key] = uid(key));\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_shared-key.js\n ** module id = 40\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_shared-key.js?");
           
/***/ },
/* 41 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var global = __webpack_require__(12);\nvar SHARED = '__core-js_shared__';\nvar store = global[SHARED] || (global[SHARED] = {});\nmodule.exports = function (key) {\n  return store[key] || (store[key] = {});\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_shared.js\n ** module id = 41\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_shared.js?");
           
/***/ },
/* 42 */
/***/ function(module, exports) {
           
           eval("var id = 0;\nvar px = Math.random();\nmodule.exports = function (key) {\n  return 'Symbol('.concat(key === undefined ? '' : key, ')_', (++id + px).toString(36));\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_uid.js\n ** module id = 42\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_uid.js?");
           
/***/ },
/* 43 */
/***/ function(module, exports) {
           
           eval("// IE 8- don't enum bug keys\nmodule.exports = (\n  'constructor,hasOwnProperty,isPrototypeOf,propertyIsEnumerable,toLocaleString,toString,valueOf'\n).split(',');\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_enum-bug-keys.js\n ** module id = 43\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_enum-bug-keys.js?");
           
/***/ },
/* 44 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var document = __webpack_require__(12).document;\nmodule.exports = document && document.documentElement;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_html.js\n ** module id = 44\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_html.js?");
           
/***/ },
/* 45 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var def = __webpack_require__(17).f;\nvar has = __webpack_require__(27);\nvar TAG = __webpack_require__(46)('toStringTag');\n\nmodule.exports = function (it, tag, stat) {\n  if (it && !has(it = stat ? it : it.prototype, TAG)) def(it, TAG, { configurable: true, value: tag });\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_set-to-string-tag.js\n ** module id = 45\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_set-to-string-tag.js?");
           
/***/ },
/* 46 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var store = __webpack_require__(41)('wks');\nvar uid = __webpack_require__(42);\nvar Symbol = __webpack_require__(12).Symbol;\nvar USE_SYMBOL = typeof Symbol == 'function';\n\nvar $exports = module.exports = function (name) {\n  return store[name] || (store[name] =\n    USE_SYMBOL && Symbol[name] || (USE_SYMBOL ? Symbol : uid)('Symbol.' + name));\n};\n\n$exports.store = store;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_wks.js\n ** module id = 46\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_wks.js?");
           
/***/ },
/* 47 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("// 19.1.2.9 / 15.2.3.2 Object.getPrototypeOf(O)\nvar has = __webpack_require__(27);\nvar toObject = __webpack_require__(48);\nvar IE_PROTO = __webpack_require__(40)('IE_PROTO');\nvar ObjectProto = Object.prototype;\n\nmodule.exports = Object.getPrototypeOf || function (O) {\n  O = toObject(O);\n  if (has(O, IE_PROTO)) return O[IE_PROTO];\n  if (typeof O.constructor == 'function' && O instanceof O.constructor) {\n    return O.constructor.prototype;\n  } return O instanceof Object ? ObjectProto : null;\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_object-gpo.js\n ** module id = 47\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_object-gpo.js?");
           
/***/ },
/* 48 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("// 7.1.13 ToObject(argument)\nvar defined = __webpack_require__(8);\nmodule.exports = function (it) {\n  return Object(defined(it));\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_to-object.js\n ** module id = 48\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_to-object.js?");
           
/***/ },
/* 49 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("__webpack_require__(50);\nvar global = __webpack_require__(12);\nvar hide = __webpack_require__(16);\nvar Iterators = __webpack_require__(28);\nvar TO_STRING_TAG = __webpack_require__(46)('toStringTag');\n\nvar DOMIterables = ('CSSRuleList,CSSStyleDeclaration,CSSValueList,ClientRectList,DOMRectList,DOMStringList,' +\n  'DOMTokenList,DataTransferItemList,FileList,HTMLAllCollection,HTMLCollection,HTMLFormElement,HTMLSelectElement,' +\n  'MediaList,MimeTypeArray,NamedNodeMap,NodeList,PaintRequestList,Plugin,PluginArray,SVGLengthList,SVGNumberList,' +\n  'SVGPathSegList,SVGPointList,SVGStringList,SVGTransformList,SourceBufferList,StyleSheetList,TextTrackCueList,' +\n  'TextTrackList,TouchList').split(',');\n\nfor (var i = 0; i < DOMIterables.length; i++) {\n  var NAME = DOMIterables[i];\n  var Collection = global[NAME];\n  var proto = Collection && Collection.prototype;\n  if (proto && !proto[TO_STRING_TAG]) hide(proto, TO_STRING_TAG, NAME);\n  Iterators[NAME] = Iterators.Array;\n}\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/web.dom.iterable.js\n ** module id = 49\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/web.dom.iterable.js?");
           
/***/ },
/* 50 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("'use strict';\nvar addToUnscopables = __webpack_require__(51);\nvar step = __webpack_require__(52);\nvar Iterators = __webpack_require__(28);\nvar toIObject = __webpack_require__(34);\n\n// 22.1.3.4 Array.prototype.entries()\n// 22.1.3.13 Array.prototype.keys()\n// 22.1.3.29 Array.prototype.values()\n// 22.1.3.30 Array.prototype[@@iterator]()\nmodule.exports = __webpack_require__(9)(Array, 'Array', function (iterated, kind) {\n  this._t = toIObject(iterated); // target\n  this._i = 0;                   // next index\n  this._k = kind;                // kind\n// 22.1.5.2.1 %ArrayIteratorPrototype%.next()\n}, function () {\n  var O = this._t;\n  var kind = this._k;\n  var index = this._i++;\n  if (!O || index >= O.length) {\n    this._t = undefined;\n    return step(1);\n  }\n  if (kind == 'keys') return step(0, index);\n  if (kind == 'values') return step(0, O[index]);\n  return step(0, [index, O[index]]);\n}, 'values');\n\n// argumentsList[@@iterator] is %ArrayProto_values% (9.4.4.6, 9.4.4.7)\nIterators.Arguments = Iterators.Array;\n\naddToUnscopables('keys');\naddToUnscopables('values');\naddToUnscopables('entries');\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/es6.array.iterator.js\n ** module id = 50\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/es6.array.iterator.js?");
           
/***/ },
/* 51 */
/***/ function(module, exports) {
           
           eval("module.exports = function () { /* empty */ };\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_add-to-unscopables.js\n ** module id = 51\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_add-to-unscopables.js?");
           
/***/ },
/* 52 */
/***/ function(module, exports) {
           
           eval("module.exports = function (done, value) {\n  return { value: value, done: !!done };\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_iter-step.js\n ** module id = 52\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_iter-step.js?");
           
/***/ },
/* 53 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("'use strict';\nvar LIBRARY = __webpack_require__(10);\nvar global = __webpack_require__(12);\nvar ctx = __webpack_require__(14);\nvar classof = __webpack_require__(54);\nvar $export = __webpack_require__(11);\nvar isObject = __webpack_require__(19);\nvar aFunction = __webpack_require__(15);\nvar anInstance = __webpack_require__(55);\nvar forOf = __webpack_require__(56);\nvar speciesConstructor = __webpack_require__(60);\nvar task = __webpack_require__(61).set;\nvar microtask = __webpack_require__(63)();\nvar newPromiseCapabilityModule = __webpack_require__(64);\nvar perform = __webpack_require__(65);\nvar promiseResolve = __webpack_require__(66);\nvar PROMISE = 'Promise';\nvar TypeError = global.TypeError;\nvar process = global.process;\nvar $Promise = global[PROMISE];\nvar isNode = classof(process) == 'process';\nvar empty = function () { /* empty */ };\nvar Internal, newGenericPromiseCapability, OwnPromiseCapability, Wrapper;\nvar newPromiseCapability = newGenericPromiseCapability = newPromiseCapabilityModule.f;\n\nvar USE_NATIVE = !!function () {\n  try {\n    // correct subclassing with @@species support\n    var promise = $Promise.resolve(1);\n    var FakePromise = (promise.constructor = {})[__webpack_require__(46)('species')] = function (exec) {\n      exec(empty, empty);\n    };\n    // unhandled rejections tracking support, NodeJS Promise without it fails @@species test\n    return (isNode || typeof PromiseRejectionEvent == 'function') && promise.then(empty) instanceof FakePromise;\n  } catch (e) { /* empty */ }\n}();\n\n// helpers\nvar sameConstructor = LIBRARY ? function (a, b) {\n  // with library wrapper special case\n  return a === b || a === $Promise && b === Wrapper;\n} : function (a, b) {\n  return a === b;\n};\nvar isThenable = function (it) {\n  var then;\n  return isObject(it) && typeof (then = it.then) == 'function' ? then : false;\n};\nvar notify = function (promise, isReject) {\n  if (promise._n) return;\n  promise._n = true;\n  var chain = promise._c;\n  microtask(function () {\n    var value = promise._v;\n    var ok = promise._s == 1;\n    var i = 0;\n    var run = function (reaction) {\n      var handler = ok ? reaction.ok : reaction.fail;\n      var resolve = reaction.resolve;\n      var reject = reaction.reject;\n      var domain = reaction.domain;\n      var result, then;\n      try {\n        if (handler) {\n          if (!ok) {\n            if (promise._h == 2) onHandleUnhandled(promise);\n            promise._h = 1;\n          }\n          if (handler === true) result = value;\n          else {\n            if (domain) domain.enter();\n            result = handler(value);\n            if (domain) domain.exit();\n          }\n          if (result === reaction.promise) {\n            reject(TypeError('Promise-chain cycle'));\n          } else if (then = isThenable(result)) {\n            then.call(result, resolve, reject);\n          } else resolve(result);\n        } else reject(value);\n      } catch (e) {\n        reject(e);\n      }\n    };\n    while (chain.length > i) run(chain[i++]); // variable length - can't use forEach\n    promise._c = [];\n    promise._n = false;\n    if (isReject && !promise._h) onUnhandled(promise);\n  });\n};\nvar onUnhandled = function (promise) {\n  task.call(global, function () {\n    var value = promise._v;\n    var unhandled = isUnhandled(promise);\n    var result, handler, console;\n    if (unhandled) {\n      result = perform(function () {\n        if (isNode) {\n          process.emit('unhandledRejection', value, promise);\n        } else if (handler = global.onunhandledrejection) {\n          handler({ promise: promise, reason: value });\n        } else if ((console = global.console) && console.error) {\n          console.error('Unhandled promise rejection', value);\n        }\n      });\n      // Browsers should not trigger `rejectionHandled` event if it was handled here, NodeJS - should\n      promise._h = isNode || isUnhandled(promise) ? 2 : 1;\n    } promise._a = undefined;\n    if (unhandled && result.e) throw result.v;\n  });\n};\nvar isUnhandled = function (promise) {\n  if (promise._h == 1) return false;\n  var chain = promise._a || promise._c;\n  var i = 0;\n  var reaction;\n  while (chain.length > i) {\n    reaction = chain[i++];\n    if (reaction.fail || !isUnhandled(reaction.promise)) return false;\n  } return true;\n};\nvar onHandleUnhandled = function (promise) {\n  task.call(global, function () {\n    var handler;\n    if (isNode) {\n      process.emit('rejectionHandled', promise);\n    } else if (handler = global.onrejectionhandled) {\n      handler({ promise: promise, reason: promise._v });\n    }\n  });\n};\nvar $reject = function (value) {\n  var promise = this;\n  if (promise._d) return;\n  promise._d = true;\n  promise = promise._w || promise; // unwrap\n  promise._v = value;\n  promise._s = 2;\n  if (!promise._a) promise._a = promise._c.slice();\n  notify(promise, true);\n};\nvar $resolve = function (value) {\n  var promise = this;\n  var then;\n  if (promise._d) return;\n  promise._d = true;\n  promise = promise._w || promise; // unwrap\n  try {\n    if (promise === value) throw TypeError(\"Promise can't be resolved itself\");\n    if (then = isThenable(value)) {\n      microtask(function () {\n        var wrapper = { _w: promise, _d: false }; // wrap\n        try {\n          then.call(value, ctx($resolve, wrapper, 1), ctx($reject, wrapper, 1));\n        } catch (e) {\n          $reject.call(wrapper, e);\n        }\n      });\n    } else {\n      promise._v = value;\n      promise._s = 1;\n      notify(promise, false);\n    }\n  } catch (e) {\n    $reject.call({ _w: promise, _d: false }, e); // wrap\n  }\n};\n\n// constructor polyfill\nif (!USE_NATIVE) {\n  // 25.4.3.1 Promise(executor)\n  $Promise = function Promise(executor) {\n    anInstance(this, $Promise, PROMISE, '_h');\n    aFunction(executor);\n    Internal.call(this);\n    try {\n      executor(ctx($resolve, this, 1), ctx($reject, this, 1));\n    } catch (err) {\n      $reject.call(this, err);\n    }\n  };\n  // eslint-disable-next-line no-unused-vars\n  Internal = function Promise(executor) {\n    this._c = [];             // <- awaiting reactions\n    this._a = undefined;      // <- checked in isUnhandled reactions\n    this._s = 0;              // <- state\n    this._d = false;          // <- done\n    this._v = undefined;      // <- value\n    this._h = 0;              // <- rejection state, 0 - default, 1 - handled, 2 - unhandled\n    this._n = false;          // <- notify\n  };\n  Internal.prototype = __webpack_require__(67)($Promise.prototype, {\n    // 25.4.5.3 Promise.prototype.then(onFulfilled, onRejected)\n    then: function then(onFulfilled, onRejected) {\n      var reaction = newPromiseCapability(speciesConstructor(this, $Promise));\n      reaction.ok = typeof onFulfilled == 'function' ? onFulfilled : true;\n      reaction.fail = typeof onRejected == 'function' && onRejected;\n      reaction.domain = isNode ? process.domain : undefined;\n      this._c.push(reaction);\n      if (this._a) this._a.push(reaction);\n      if (this._s) notify(this, false);\n      return reaction.promise;\n    },\n    // 25.4.5.1 Promise.prototype.catch(onRejected)\n    'catch': function (onRejected) {\n      return this.then(undefined, onRejected);\n    }\n  });\n  OwnPromiseCapability = function () {\n    var promise = new Internal();\n    this.promise = promise;\n    this.resolve = ctx($resolve, promise, 1);\n    this.reject = ctx($reject, promise, 1);\n  };\n  newPromiseCapabilityModule.f = newPromiseCapability = function (C) {\n    return sameConstructor($Promise, C)\n      ? new OwnPromiseCapability(C)\n      : newGenericPromiseCapability(C);\n  };\n}\n\n$export($export.G + $export.W + $export.F * !USE_NATIVE, { Promise: $Promise });\n__webpack_require__(45)($Promise, PROMISE);\n__webpack_require__(68)(PROMISE);\nWrapper = __webpack_require__(13)[PROMISE];\n\n// statics\n$export($export.S + $export.F * !USE_NATIVE, PROMISE, {\n  // 25.4.4.5 Promise.reject(r)\n  reject: function reject(r) {\n    var capability = newPromiseCapability(this);\n    var $$reject = capability.reject;\n    $$reject(r);\n    return capability.promise;\n  }\n});\n$export($export.S + $export.F * (LIBRARY || !USE_NATIVE), PROMISE, {\n  // 25.4.4.6 Promise.resolve(x)\n  resolve: function resolve(x) {\n    // instanceof instead of internal slot check because we should fix it without replacement native Promise core\n    if (x instanceof $Promise && sameConstructor(x.constructor, this)) return x;\n    return promiseResolve(this, x);\n  }\n});\n$export($export.S + $export.F * !(USE_NATIVE && __webpack_require__(69)(function (iter) {\n  $Promise.all(iter)['catch'](empty);\n})), PROMISE, {\n  // 25.4.4.1 Promise.all(iterable)\n  all: function all(iterable) {\n    var C = this;\n    var capability = newPromiseCapability(C);\n    var resolve = capability.resolve;\n    var reject = capability.reject;\n    var result = perform(function () {\n      var values = [];\n      var index = 0;\n      var remaining = 1;\n      forOf(iterable, false, function (promise) {\n        var $index = index++;\n        var alreadyCalled = false;\n        values.push(undefined);\n        remaining++;\n        C.resolve(promise).then(function (value) {\n          if (alreadyCalled) return;\n          alreadyCalled = true;\n          values[$index] = value;\n          --remaining || resolve(values);\n        }, reject);\n      });\n      --remaining || resolve(values);\n    });\n    if (result.e) reject(result.v);\n    return capability.promise;\n  },\n  // 25.4.4.4 Promise.race(iterable)\n  race: function race(iterable) {\n    var C = this;\n    var capability = newPromiseCapability(C);\n    var reject = capability.reject;\n    var result = perform(function () {\n      forOf(iterable, false, function (promise) {\n        C.resolve(promise).then(capability.resolve, reject);\n      });\n    });\n    if (result.e) reject(result.v);\n    return capability.promise;\n  }\n});\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/es6.promise.js\n ** module id = 53\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/es6.promise.js?");
           
/***/ },
/* 54 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("// getting tag from 19.1.3.6 Object.prototype.toString()\nvar cof = __webpack_require__(36);\nvar TAG = __webpack_require__(46)('toStringTag');\n// ES3 wrong here\nvar ARG = cof(function () { return arguments; }()) == 'Arguments';\n\n// fallback for IE11 Script Access Denied error\nvar tryGet = function (it, key) {\n  try {\n    return it[key];\n  } catch (e) { /* empty */ }\n};\n\nmodule.exports = function (it) {\n  var O, T, B;\n  return it === undefined ? 'Undefined' : it === null ? 'Null'\n    // @@toStringTag case\n    : typeof (T = tryGet(O = Object(it), TAG)) == 'string' ? T\n    // builtinTag case\n    : ARG ? cof(O)\n    // ES3 arguments fallback\n    : (B = cof(O)) == 'Object' && typeof O.callee == 'function' ? 'Arguments' : B;\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_classof.js\n ** module id = 54\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_classof.js?");
           
/***/ },
/* 55 */
/***/ function(module, exports) {
           
           eval("module.exports = function (it, Constructor, name, forbiddenField) {\n  if (!(it instanceof Constructor) || (forbiddenField !== undefined && forbiddenField in it)) {\n    throw TypeError(name + ': incorrect invocation!');\n  } return it;\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_an-instance.js\n ** module id = 55\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_an-instance.js?");
           
/***/ },
/* 56 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var ctx = __webpack_require__(14);\nvar call = __webpack_require__(57);\nvar isArrayIter = __webpack_require__(58);\nvar anObject = __webpack_require__(18);\nvar toLength = __webpack_require__(38);\nvar getIterFn = __webpack_require__(59);\nvar BREAK = {};\nvar RETURN = {};\nvar exports = module.exports = function (iterable, entries, fn, that, ITERATOR) {\n  var iterFn = ITERATOR ? function () { return iterable; } : getIterFn(iterable);\n  var f = ctx(fn, that, entries ? 2 : 1);\n  var index = 0;\n  var length, step, iterator, result;\n  if (typeof iterFn != 'function') throw TypeError(iterable + ' is not iterable!');\n  // fast case for arrays with default iterator\n  if (isArrayIter(iterFn)) for (length = toLength(iterable.length); length > index; index++) {\n    result = entries ? f(anObject(step = iterable[index])[0], step[1]) : f(iterable[index]);\n    if (result === BREAK || result === RETURN) return result;\n  } else for (iterator = iterFn.call(iterable); !(step = iterator.next()).done;) {\n    result = call(iterator, f, step.value, entries);\n    if (result === BREAK || result === RETURN) return result;\n  }\n};\nexports.BREAK = BREAK;\nexports.RETURN = RETURN;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_for-of.js\n ** module id = 56\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_for-of.js?");
           
/***/ },
/* 57 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("// call something on iterator step with safe closing on error\nvar anObject = __webpack_require__(18);\nmodule.exports = function (iterator, fn, value, entries) {\n  try {\n    return entries ? fn(anObject(value)[0], value[1]) : fn(value);\n  // 7.4.6 IteratorClose(iterator, completion)\n  } catch (e) {\n    var ret = iterator['return'];\n    if (ret !== undefined) anObject(ret.call(iterator));\n    throw e;\n  }\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_iter-call.js\n ** module id = 57\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_iter-call.js?");
           
/***/ },
/* 58 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("// check on default Array iterator\nvar Iterators = __webpack_require__(28);\nvar ITERATOR = __webpack_require__(46)('iterator');\nvar ArrayProto = Array.prototype;\n\nmodule.exports = function (it) {\n  return it !== undefined && (Iterators.Array === it || ArrayProto[ITERATOR] === it);\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_is-array-iter.js\n ** module id = 58\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_is-array-iter.js?");
           
/***/ },
/* 59 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var classof = __webpack_require__(54);\nvar ITERATOR = __webpack_require__(46)('iterator');\nvar Iterators = __webpack_require__(28);\nmodule.exports = __webpack_require__(13).getIteratorMethod = function (it) {\n  if (it != undefined) return it[ITERATOR]\n    || it['@@iterator']\n    || Iterators[classof(it)];\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/core.get-iterator-method.js\n ** module id = 59\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/core.get-iterator-method.js?");
           
/***/ },
/* 60 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("// 7.3.20 SpeciesConstructor(O, defaultConstructor)\nvar anObject = __webpack_require__(18);\nvar aFunction = __webpack_require__(15);\nvar SPECIES = __webpack_require__(46)('species');\nmodule.exports = function (O, D) {\n  var C = anObject(O).constructor;\n  var S;\n  return C === undefined || (S = anObject(C)[SPECIES]) == undefined ? D : aFunction(S);\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_species-constructor.js\n ** module id = 60\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_species-constructor.js?");
           
/***/ },
/* 61 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var ctx = __webpack_require__(14);\nvar invoke = __webpack_require__(62);\nvar html = __webpack_require__(44);\nvar cel = __webpack_require__(23);\nvar global = __webpack_require__(12);\nvar process = global.process;\nvar setTask = global.setImmediate;\nvar clearTask = global.clearImmediate;\nvar MessageChannel = global.MessageChannel;\nvar Dispatch = global.Dispatch;\nvar counter = 0;\nvar queue = {};\nvar ONREADYSTATECHANGE = 'onreadystatechange';\nvar defer, channel, port;\nvar run = function () {\n  var id = +this;\n  // eslint-disable-next-line no-prototype-builtins\n  if (queue.hasOwnProperty(id)) {\n    var fn = queue[id];\n    delete queue[id];\n    fn();\n  }\n};\nvar listener = function (event) {\n  run.call(event.data);\n};\n// Node.js 0.9+ & IE10+ has setImmediate, otherwise:\nif (!setTask || !clearTask) {\n  setTask = function setImmediate(fn) {\n    var args = [];\n    var i = 1;\n    while (arguments.length > i) args.push(arguments[i++]);\n    queue[++counter] = function () {\n      // eslint-disable-next-line no-new-func\n      invoke(typeof fn == 'function' ? fn : Function(fn), args);\n    };\n    defer(counter);\n    return counter;\n  };\n  clearTask = function clearImmediate(id) {\n    delete queue[id];\n  };\n  // Node.js 0.8-\n  if (__webpack_require__(36)(process) == 'process') {\n    defer = function (id) {\n      process.nextTick(ctx(run, id, 1));\n    };\n  // Sphere (JS game engine) Dispatch API\n  } else if (Dispatch && Dispatch.now) {\n    defer = function (id) {\n      Dispatch.now(ctx(run, id, 1));\n    };\n  // Browsers with MessageChannel, includes WebWorkers\n  } else if (MessageChannel) {\n    channel = new MessageChannel();\n    port = channel.port2;\n    channel.port1.onmessage = listener;\n    defer = ctx(port.postMessage, port, 1);\n  // Browsers with postMessage, skip WebWorkers\n  // IE8 has postMessage, but it's sync & typeof its postMessage is 'object'\n  } else if (global.addEventListener && typeof postMessage == 'function' && !global.importScripts) {\n    defer = function (id) {\n      global.postMessage(id + '', '*');\n    };\n    global.addEventListener('message', listener, false);\n  // IE8-\n  } else if (ONREADYSTATECHANGE in cel('script')) {\n    defer = function (id) {\n      html.appendChild(cel('script'))[ONREADYSTATECHANGE] = function () {\n        html.removeChild(this);\n        run.call(id);\n      };\n    };\n  // Rest old browsers\n  } else {\n    defer = function (id) {\n      setTimeout(ctx(run, id, 1), 0);\n    };\n  }\n}\nmodule.exports = {\n  set: setTask,\n  clear: clearTask\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_task.js\n ** module id = 61\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_task.js?");
           
/***/ },
/* 62 */
/***/ function(module, exports) {
           
           eval("// fast apply, http://jsperf.lnkit.com/fast-apply/5\nmodule.exports = function (fn, args, that) {\n  var un = that === undefined;\n  switch (args.length) {\n    case 0: return un ? fn()\n                      : fn.call(that);\n    case 1: return un ? fn(args[0])\n                      : fn.call(that, args[0]);\n    case 2: return un ? fn(args[0], args[1])\n                      : fn.call(that, args[0], args[1]);\n    case 3: return un ? fn(args[0], args[1], args[2])\n                      : fn.call(that, args[0], args[1], args[2]);\n    case 4: return un ? fn(args[0], args[1], args[2], args[3])\n                      : fn.call(that, args[0], args[1], args[2], args[3]);\n  } return fn.apply(that, args);\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_invoke.js\n ** module id = 62\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_invoke.js?");
           
/***/ },
/* 63 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var global = __webpack_require__(12);\nvar macrotask = __webpack_require__(61).set;\nvar Observer = global.MutationObserver || global.WebKitMutationObserver;\nvar process = global.process;\nvar Promise = global.Promise;\nvar isNode = __webpack_require__(36)(process) == 'process';\n\nmodule.exports = function () {\n  var head, last, notify;\n\n  var flush = function () {\n    var parent, fn;\n    if (isNode && (parent = process.domain)) parent.exit();\n    while (head) {\n      fn = head.fn;\n      head = head.next;\n      try {\n        fn();\n      } catch (e) {\n        if (head) notify();\n        else last = undefined;\n        throw e;\n      }\n    } last = undefined;\n    if (parent) parent.enter();\n  };\n\n  // Node.js\n  if (isNode) {\n    notify = function () {\n      process.nextTick(flush);\n    };\n  // browsers with MutationObserver\n  } else if (Observer) {\n    var toggle = true;\n    var node = document.createTextNode('');\n    new Observer(flush).observe(node, { characterData: true }); // eslint-disable-line no-new\n    notify = function () {\n      node.data = toggle = !toggle;\n    };\n  // environments with maybe non-completely correct, but existent Promise\n  } else if (Promise && Promise.resolve) {\n    var promise = Promise.resolve();\n    notify = function () {\n      promise.then(flush);\n    };\n  // for other environments - macrotask based on:\n  // - setImmediate\n  // - MessageChannel\n  // - window.postMessag\n  // - onreadystatechange\n  // - setTimeout\n  } else {\n    notify = function () {\n      // strange IE + webpack dev server bug - use .call(global)\n      macrotask.call(global, flush);\n    };\n  }\n\n  return function (fn) {\n    var task = { fn: fn, next: undefined };\n    if (last) last.next = task;\n    if (!head) {\n      head = task;\n      notify();\n    } last = task;\n  };\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_microtask.js\n ** module id = 63\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_microtask.js?");
           
/***/ },
/* 64 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("'use strict';\n// 25.4.1.5 NewPromiseCapability(C)\nvar aFunction = __webpack_require__(15);\n\nfunction PromiseCapability(C) {\n  var resolve, reject;\n  this.promise = new C(function ($$resolve, $$reject) {\n    if (resolve !== undefined || reject !== undefined) throw TypeError('Bad Promise constructor');\n    resolve = $$resolve;\n    reject = $$reject;\n  });\n  this.resolve = aFunction(resolve);\n  this.reject = aFunction(reject);\n}\n\nmodule.exports.f = function (C) {\n  return new PromiseCapability(C);\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_new-promise-capability.js\n ** module id = 64\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_new-promise-capability.js?");
           
/***/ },
/* 65 */
/***/ function(module, exports) {
           
           eval("module.exports = function (exec) {\n  try {\n    return { e: false, v: exec() };\n  } catch (e) {\n    return { e: true, v: e };\n  }\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_perform.js\n ** module id = 65\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_perform.js?");
           
/***/ },
/* 66 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var newPromiseCapability = __webpack_require__(64);\n\nmodule.exports = function (C, x) {\n  var promiseCapability = newPromiseCapability.f(C);\n  var resolve = promiseCapability.resolve;\n  resolve(x);\n  return promiseCapability.promise;\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_promise-resolve.js\n ** module id = 66\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_promise-resolve.js?");
           
/***/ },
/* 67 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var hide = __webpack_require__(16);\nmodule.exports = function (target, src, safe) {\n  for (var key in src) {\n    if (safe && target[key]) target[key] = src[key];\n    else hide(target, key, src[key]);\n  } return target;\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_redefine-all.js\n ** module id = 67\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_redefine-all.js?");
           
/***/ },
/* 68 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("'use strict';\nvar global = __webpack_require__(12);\nvar core = __webpack_require__(13);\nvar dP = __webpack_require__(17);\nvar DESCRIPTORS = __webpack_require__(21);\nvar SPECIES = __webpack_require__(46)('species');\n\nmodule.exports = function (KEY) {\n  var C = typeof core[KEY] == 'function' ? core[KEY] : global[KEY];\n  if (DESCRIPTORS && C && !C[SPECIES]) dP.f(C, SPECIES, {\n    configurable: true,\n    get: function () { return this; }\n  });\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_set-species.js\n ** module id = 68\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_set-species.js?");
           
/***/ },
/* 69 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var ITERATOR = __webpack_require__(46)('iterator');\nvar SAFE_CLOSING = false;\n\ntry {\n  var riter = [7][ITERATOR]();\n  riter['return'] = function () { SAFE_CLOSING = true; };\n  // eslint-disable-next-line no-throw-literal\n  Array.from(riter, function () { throw 2; });\n} catch (e) { /* empty */ }\n\nmodule.exports = function (exec, skipClosing) {\n  if (!skipClosing && !SAFE_CLOSING) return false;\n  var safe = false;\n  try {\n    var arr = [7];\n    var iter = arr[ITERATOR]();\n    iter.next = function () { return { done: safe = true }; };\n    arr[ITERATOR] = function () { return iter; };\n    exec(arr);\n  } catch (e) { /* empty */ }\n  return safe;\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/_iter-detect.js\n ** module id = 69\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/_iter-detect.js?");
           
/***/ },
/* 70 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("// https://github.com/tc39/proposal-promise-finally\n'use strict';\nvar $export = __webpack_require__(11);\nvar core = __webpack_require__(13);\nvar global = __webpack_require__(12);\nvar speciesConstructor = __webpack_require__(60);\nvar promiseResolve = __webpack_require__(66);\n\n$export($export.P + $export.R, 'Promise', { 'finally': function (onFinally) {\n  var C = speciesConstructor(this, core.Promise || global.Promise);\n  var isFunction = typeof onFinally == 'function';\n  return this.then(\n    isFunction ? function (x) {\n      return promiseResolve(C, onFinally()).then(function () { return x; });\n    } : onFinally,\n    isFunction ? function (e) {\n      return promiseResolve(C, onFinally()).then(function () { throw e; });\n    } : onFinally\n  );\n} });\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/es7.promise.finally.js\n ** module id = 70\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/es7.promise.finally.js?");
           
/***/ },
/* 71 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("'use strict';\n// https://github.com/tc39/proposal-promise-try\nvar $export = __webpack_require__(11);\nvar newPromiseCapability = __webpack_require__(64);\nvar perform = __webpack_require__(65);\n\n$export($export.S, 'Promise', { 'try': function (callbackfn) {\n  var promiseCapability = newPromiseCapability.f(this);\n  var result = perform(callbackfn);\n  (result.e ? promiseCapability.reject : promiseCapability.resolve)(result.v);\n  return promiseCapability.promise;\n} });\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/es7.promise.try.js\n ** module id = 71\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/es7.promise.try.js?");
           
/***/ },
/* 72 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("module.exports = { \"default\": __webpack_require__(73), __esModule: true };\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_babel-runtime@6.25.0@babel-runtime/core-js/object/create.js\n ** module id = 72\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_babel-runtime@6.25.0@babel-runtime/core-js/object/create.js?");
           
/***/ },
/* 73 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("__webpack_require__(74);\nvar $Object = __webpack_require__(13).Object;\nmodule.exports = function create(P, D) {\n  return $Object.create(P, D);\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/fn/object/create.js\n ** module id = 73\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/fn/object/create.js?");
           
/***/ },
/* 74 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var $export = __webpack_require__(11);\n// 19.1.2.2 / 15.2.3.5 Object.create(O [, Properties])\n$export($export.S, 'Object', { create: __webpack_require__(30) });\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/modules/es6.object.create.js\n ** module id = 74\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/modules/es6.object.create.js?");
           
/***/ },
/* 75 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var baseGetTag = __webpack_require__(76),\n    isObject = __webpack_require__(82);\n\n/** `Object#toString` result references. */\nvar asyncTag = '[object AsyncFunction]',\n    funcTag = '[object Function]',\n    genTag = '[object GeneratorFunction]',\n    proxyTag = '[object Proxy]';\n\n/**\n * Checks if `value` is classified as a `Function` object.\n *\n * @static\n * @memberOf _\n * @since 0.1.0\n * @category Lang\n * @param {*} value The value to check.\n * @returns {boolean} Returns `true` if `value` is a function, else `false`.\n * @example\n *\n * _.isFunction(_);\n * // => true\n *\n * _.isFunction(/abc/);\n * // => false\n */\nfunction isFunction(value) {\n  if (!isObject(value)) {\n    return false;\n  }\n  // The use of `Object#toString` avoids issues with the `typeof` operator\n  // in Safari 9 which returns 'object' for typed arrays and other constructors.\n  var tag = baseGetTag(value);\n  return tag == funcTag || tag == genTag || tag == asyncTag || tag == proxyTag;\n}\n\nmodule.exports = isFunction;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/isFunction.js\n ** module id = 75\n ** module chunks = 0 1 2 3 4 5 6\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/isFunction.js?");
           
/***/ },
/* 76 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var Symbol = __webpack_require__(77),\n    getRawTag = __webpack_require__(80),\n    objectToString = __webpack_require__(81);\n\n/** `Object#toString` result references. */\nvar nullTag = '[object Null]',\n    undefinedTag = '[object Undefined]';\n\n/** Built-in value references. */\nvar symToStringTag = Symbol ? Symbol.toStringTag : undefined;\n\n/**\n * The base implementation of `getTag` without fallbacks for buggy environments.\n *\n * @private\n * @param {*} value The value to query.\n * @returns {string} Returns the `toStringTag`.\n */\nfunction baseGetTag(value) {\n  if (value == null) {\n    return value === undefined ? undefinedTag : nullTag;\n  }\n  return (symToStringTag && symToStringTag in Object(value))\n    ? getRawTag(value)\n    : objectToString(value);\n}\n\nmodule.exports = baseGetTag;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/_baseGetTag.js\n ** module id = 76\n ** module chunks = 0 1 2 3 4 5 6\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/_baseGetTag.js?");
           
/***/ },
/* 77 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var root = __webpack_require__(78);\n\n/** Built-in value references. */\nvar Symbol = root.Symbol;\n\nmodule.exports = Symbol;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/_Symbol.js\n ** module id = 77\n ** module chunks = 0 1 2 3 4 5 6\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/_Symbol.js?");
           
/***/ },
/* 78 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var freeGlobal = __webpack_require__(79);\n\n/** Detect free variable `self`. */\nvar freeSelf = typeof self == 'object' && self && self.Object === Object && self;\n\n/** Used as a reference to the global object. */\nvar root = freeGlobal || freeSelf || Function('return this')();\n\nmodule.exports = root;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/_root.js\n ** module id = 78\n ** module chunks = 0 1 2 3 4 5 6\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/_root.js?");
           
/***/ },
/* 79 */
/***/ function(module, exports) {
           
           eval("/* WEBPACK VAR INJECTION */(function(global) {/** Detect free variable `global` from Node.js. */\nvar freeGlobal = typeof global == 'object' && global && global.Object === Object && global;\n\nmodule.exports = freeGlobal;\n\n/* WEBPACK VAR INJECTION */}.call(exports, (function() { return this; }())))\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/_freeGlobal.js\n ** module id = 79\n ** module chunks = 0 1 2 3 4 5 6\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/_freeGlobal.js?");
           
/***/ },
/* 80 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var Symbol = __webpack_require__(77);\n\n/** Used for built-in method references. */\nvar objectProto = Object.prototype;\n\n/** Used to check objects for own properties. */\nvar hasOwnProperty = objectProto.hasOwnProperty;\n\n/**\n * Used to resolve the\n * [`toStringTag`](http://ecma-international.org/ecma-262/7.0/#sec-object.prototype.tostring)\n * of values.\n */\nvar nativeObjectToString = objectProto.toString;\n\n/** Built-in value references. */\nvar symToStringTag = Symbol ? Symbol.toStringTag : undefined;\n\n/**\n * A specialized version of `baseGetTag` which ignores `Symbol.toStringTag` values.\n *\n * @private\n * @param {*} value The value to query.\n * @returns {string} Returns the raw `toStringTag`.\n */\nfunction getRawTag(value) {\n  var isOwn = hasOwnProperty.call(value, symToStringTag),\n      tag = value[symToStringTag];\n\n  try {\n    value[symToStringTag] = undefined;\n    var unmasked = true;\n  } catch (e) {}\n\n  var result = nativeObjectToString.call(value);\n  if (unmasked) {\n    if (isOwn) {\n      value[symToStringTag] = tag;\n    } else {\n      delete value[symToStringTag];\n    }\n  }\n  return result;\n}\n\nmodule.exports = getRawTag;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/_getRawTag.js\n ** module id = 80\n ** module chunks = 0 1 2 3 4 5 6\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/_getRawTag.js?");
           
/***/ },
/* 81 */
/***/ function(module, exports) {
           
           eval("/** Used for built-in method references. */\nvar objectProto = Object.prototype;\n\n/**\n * Used to resolve the\n * [`toStringTag`](http://ecma-international.org/ecma-262/7.0/#sec-object.prototype.tostring)\n * of values.\n */\nvar nativeObjectToString = objectProto.toString;\n\n/**\n * Converts `value` to a string using `Object.prototype.toString`.\n *\n * @private\n * @param {*} value The value to convert.\n * @returns {string} Returns the converted string.\n */\nfunction objectToString(value) {\n  return nativeObjectToString.call(value);\n}\n\nmodule.exports = objectToString;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/_objectToString.js\n ** module id = 81\n ** module chunks = 0 1 2 3 4 5 6\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/_objectToString.js?");
           
/***/ },
/* 82 */
/***/ function(module, exports) {
           
           eval("/**\n * Checks if `value` is the\n * [language type](http://www.ecma-international.org/ecma-262/7.0/#sec-ecmascript-language-types)\n * of `Object`. (e.g. arrays, functions, objects, regexes, `new Number(0)`, and `new String('')`)\n *\n * @static\n * @memberOf _\n * @since 0.1.0\n * @category Lang\n * @param {*} value The value to check.\n * @returns {boolean} Returns `true` if `value` is an object, else `false`.\n * @example\n *\n * _.isObject({});\n * // => true\n *\n * _.isObject([1, 2, 3]);\n * // => true\n *\n * _.isObject(_.noop);\n * // => true\n *\n * _.isObject(null);\n * // => false\n */\nfunction isObject(value) {\n  var type = typeof value;\n  return value != null && (type == 'object' || type == 'function');\n}\n\nmodule.exports = isObject;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/isObject.js\n ** module id = 82\n ** module chunks = 0 1 2 3 4 5 6\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/isObject.js?");
           
/***/ },
/* 83 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("'use strict';\n\nvar _promise = __webpack_require__(2);\n\nvar _promise2 = _interopRequireDefault(_promise);\n\nvar _create = __webpack_require__(72);\n\nvar _create2 = _interopRequireDefault(_create);\n\nvar _isFunction = __webpack_require__(75);\n\nvar _isFunction2 = _interopRequireDefault(_isFunction);\n\nfunction _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }\n\nvar modal = weex.requireModule('bmModal'),\n    Notice = (0, _create2.default)(null);\n\nNotice.install = function (Vue, options) {\n    Vue.prototype.$notice = {\n        alert: function alert(options) {\n            if (options.message) {\n                return new _promise2.default(function (resolve, reject) {\n                    modal.alert({\n                        titleAlign: options.titleAlign || 'center',\n                        title: options.title || '',\n                        message: options.message || '',\n                        messageAlign: options.messageAlign || 'center',\n                        okTitle: options.okTitle || '确定'\n                    }, function (params) {\n                        if ((0, _isFunction2.default)(options.callback)) {\n                            options.callback.call(params);\n                        }\n                        resolve();\n                    });\n                });\n            }\n        },\n        confirm: function confirm(options) {\n            if (options.message) {\n                return new _promise2.default(function (resolve, reject) {\n                    modal.confirm({\n                        titleAlign: options.titleAlign || 'center',\n                        title: options.title || '',\n                        message: options.message || '',\n                        messageAlign: options.messageAlign || 'center',\n                        cancelTitle: options.cancelTitle || '取消',\n                        okTitle: options.okTitle || '确定'\n                    }, function (params) {\n                        if ((0, _isFunction2.default)(options.cancelCallback)) {\n                            options.cancelCallback.call(params);\n                        }\n                        reject();\n                    }, function (params) {\n                        if ((0, _isFunction2.default)(options.okCallback)) {\n                            options.okCallback.call(params);\n                        }\n                        resolve();\n                    });\n                });\n            }\n        },\n\n        loading: {\n            show: function show() {\n                var message = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : '';\n\n                modal.showLoading({ message: message });\n            },\n            hide: function hide() {\n                setTimeout(function () {\n                    modal.hideLoading();\n                }, 200);\n            }\n        },\n        toast: function toast(options) {\n            if (!options.message) return;\n            modal.toast({\n                message: options.message,\n                duration: options.duration || 2000\n            });\n        }\n    };\n};\n\nVue.use(Notice);\n\n/*****************\n ** WEBPACK FOOTER\n ** ./src/js/widget/notice.js\n ** module id = 83\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./src/js/widget/notice.js?");
           
/***/ },
/* 84 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("'use strict';\n\nObject.defineProperty(exports, \"__esModule\", {\n    value: true\n});\nexports.DEFAULT_ANIMATETYPE = undefined;\n\nvar _stringify = __webpack_require__(85);\n\nvar _stringify2 = _interopRequireDefault(_stringify);\n\nvar _promise = __webpack_require__(2);\n\nvar _promise2 = _interopRequireDefault(_promise);\n\nvar _isEmpty = __webpack_require__(87);\n\nvar _isEmpty2 = _interopRequireDefault(_isEmpty);\n\nvar _isFunction = __webpack_require__(75);\n\nvar _isFunction2 = _interopRequireDefault(_isFunction);\n\nvar _isPlainObject = __webpack_require__(117);\n\nvar _isPlainObject2 = _interopRequireDefault(_isPlainObject);\n\nfunction _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }\n\n/**\n * @Author: songqi\n * @Date:   2017-01-11\n * @Last modified by:   songqi\n * @Last modified time: 2017-04-05\n */\n\nvar modal = weex.requireModule('bmModal'),\n    router = weex.requireModule('bmRouter'),\n    storage = weex.requireModule('bmStorage'),\n    globalEvent = weex.requireModule('globalEvent');\n\n// 客户端默认打开页面的动画\nvar DEFAULT_ANIMATETYPE = exports.DEFAULT_ANIMATETYPE = 'PUSH';\n\nvar Router = {\n    // 页面将要出现\n    viewWillAppear: [],\n    // 页面已经出现\n    viewDidAppear: [],\n    // 页面被拿出栈时即将展示\n    viewWillBackAppear: [],\n    // 页面被拿出栈时已经展示\n    viewDidBackAppear: [],\n    // 页面将要放在栈中\n    viewWillDisappear: [],\n    // 页面已经放在栈中\n    viewDidDisappear: []\n};\n\nglobalEvent.addEventListener(\"viewWillAppear\", function (options) {\n    if (options.type === 'open' || options.type === 'refresh') {\n        router.getParams(function (params) {\n            Router.viewWillAppear.map(function (item) {\n                item(params, options);\n            });\n        });\n    } else if (options.type === 'back') {\n        storage.getData('router.backParams', function (resData) {\n            Router.viewWillBackAppear.map(function (item) {\n                item(JSON.parse(resData.data.value || '{}'), options);\n            });\n        });\n    }\n});\n\nglobalEvent.addEventListener(\"viewDidAppear\", function (options) {\n    if (options.type === 'open' || options.type === 'refresh') {\n        router.getParams(function (params) {\n            Router.viewDidAppear.map(function (item) {\n                item(params, options);\n            });\n        });\n    } else if (options.type === 'back') {\n        storage.getData('router.backParams', function (resData) {\n            Router.viewDidBackAppear.map(function (item) {\n                item(JSON.parse(resData.data.value || '{}'), options);\n            });\n            storage.deleteData('router.backParams');\n        });\n    }\n});\n\nglobalEvent.addEventListener(\"viewWillDisappear\", function (options) {\n    modal.hideLoading();\n    Router.viewWillDisappear.map(function (item) {\n        item(options);\n    });\n});\n\nglobalEvent.addEventListener(\"viewDidDisappear\", function (options) {\n    Router.viewDidDisappear.map(function (item) {\n        item(options);\n    });\n});\n\n// todo 修改逻辑\nRouter.install = function (Vue, options) {\n    Vue.mixin({\n        beforeCreate: function beforeCreate() {\n            if (this.$options.bmRouter) {\n                var bmRouter = this.$options.bmRouter;\n                for (var i in bmRouter) {\n                    if (!Router[i]) {\n                        Router[i] = [];\n                    }\n                    Router[i].push(bmRouter[i].bind(this));\n                }\n            }\n        }\n    });\n    Vue.prototype.$router = {\n        open: function open(options) {\n            var _this = this;\n\n            options = options || {};\n            var currentPageInfo = this.getUrl(options.name);\n            if (currentPageInfo && currentPageInfo.url) {\n                if (options.hideNavbar && !options.statusBarStyle) {\n                    options.statusBarStyle = 'Default';\n                }\n                return new _promise2.default(function (resolve, reject) {\n                    router.open({\n                        url: currentPageInfo.url,\n                        animateType: options.animateType || DEFAULT_ANIMATETYPE,\n                        params: options.params || {},\n                        forbidBack: options.forbidBack || false,\n                        needBackCallback: (0, _isFunction2.default)(options.backCallback),\n                        navigationInfo: {\n                            statusBarStyle: options.statusBarStyle || 'default',\n                            hideNavbar: options.hideNavbar || !currentPageInfo.title || false,\n                            title: options.title || currentPageInfo.title\n                        }\n                    }, function (data) {\n                        if ((0, _isFunction2.default)(options.backCallback)) {\n                            options.backCallback.call(_this, data);\n                        }\n                    });\n                });\n            }\n        },\n        back: function back(options) {\n            var _this2 = this;\n\n            options = options || {};\n            return new _promise2.default(function (resolve, reject) {\n                router.back({\n                    length: options.length || 1,\n                    animateType: options.animateType || DEFAULT_ANIMATETYPE,\n                    params: options.params || {}\n                }, function (data) {\n                    if ((0, _isFunction2.default)(options.callback)) {\n                        options.callback.call(_this2, data);\n                    }\n                    resolve(data);\n                });\n            });\n        },\n        getParams: function getParams(callback) {\n            var _this3 = this;\n\n            return new _promise2.default(function (resolve, reject) {\n                router.getParams(function (params) {\n                    if ((0, _isFunction2.default)(callback)) {\n                        callback.call(_this3, params);\n                    }\n                    resolve(params);\n                });\n            });\n        },\n        getUrl: function getUrl(page) {\n            var currentPageInfo = Vue.prototype.eros.pages[page];\n            if (!currentPageInfo) {\n                modal.alert({\n                    message: '跳转页面不存在',\n                    okTitle: '确定'\n                });\n                return false;\n            }\n            return currentPageInfo;\n        },\n        refresh: function refresh() {\n            router.refreshWeex();\n        },\n        backHome: function backHome(homePageIndex) {\n            router.backHome({\n                homePageIndex: homePageIndex || 0\n            });\n        },\n        setBackParams: function setBackParams(params) {\n            if ((0, _isPlainObject2.default)(params)) {\n                storage.setData('router.backParams', (0, _stringify2.default)(params));\n            }\n        },\n        toWebView: function toWebView(params) {\n            if (!params.url) {\n                return;\n            }\n            params.title = params.title || 'weex-eros';\n            // params.shareInfo = {\n            //     title: params.shareTitle,\n            //     content: params.content || '',\n            //     image: params.image || '',\n            //     url: params.url || '',\n            //     platforms: params.platforms || [] // 传空的话默认全部\n            // }\n            if (params.shareInfo) {\n                !params.shareInfo.image && (params.shareInfo.image = 'https://img.benmu-health.com/wechat/jyt100.png');\n            }\n\n            router.toWebView(params);\n        },\n        toMap: function toMap(cfg) {\n            if (!cfg.destination) return;\n            router.toMap({\n                destination: cfg.destination\n            });\n        },\n        toCallPhone: function toCallPhone(phone) {\n            if (phone) {\n                router.callPhone({\n                    phone: phone\n                });\n            }\n        }\n    };\n};\n\nVue.use(Router);\n\n/*****************\n ** WEBPACK FOOTER\n ** ./src/js/widget/router.js\n ** module id = 84\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./src/js/widget/router.js?");
           
/***/ },
/* 85 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("module.exports = { \"default\": __webpack_require__(86), __esModule: true };\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_babel-runtime@6.25.0@babel-runtime/core-js/json/stringify.js\n ** module id = 85\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_babel-runtime@6.25.0@babel-runtime/core-js/json/stringify.js?");
           
/***/ },
/* 86 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var core = __webpack_require__(13);\nvar $JSON = core.JSON || (core.JSON = { stringify: JSON.stringify });\nmodule.exports = function stringify(it) { // eslint-disable-line no-unused-vars\n  return $JSON.stringify.apply($JSON, arguments);\n};\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_core-js@2.5.0@core-js/library/fn/json/stringify.js\n ** module id = 86\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_core-js@2.5.0@core-js/library/fn/json/stringify.js?");
           
/***/ },
/* 87 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var baseKeys = __webpack_require__(88),\n    getTag = __webpack_require__(92),\n    isArguments = __webpack_require__(104),\n    isArray = __webpack_require__(107),\n    isArrayLike = __webpack_require__(108),\n    isBuffer = __webpack_require__(110),\n    isPrototype = __webpack_require__(89),\n    isTypedArray = __webpack_require__(113);\n\n/** `Object#toString` result references. */\nvar mapTag = '[object Map]',\n    setTag = '[object Set]';\n\n/** Used for built-in method references. */\nvar objectProto = Object.prototype;\n\n/** Used to check objects for own properties. */\nvar hasOwnProperty = objectProto.hasOwnProperty;\n\n/**\n * Checks if `value` is an empty object, collection, map, or set.\n *\n * Objects are considered empty if they have no own enumerable string keyed\n * properties.\n *\n * Array-like values such as `arguments` objects, arrays, buffers, strings, or\n * jQuery-like collections are considered empty if they have a `length` of `0`.\n * Similarly, maps and sets are considered empty if they have a `size` of `0`.\n *\n * @static\n * @memberOf _\n * @since 0.1.0\n * @category Lang\n * @param {*} value The value to check.\n * @returns {boolean} Returns `true` if `value` is empty, else `false`.\n * @example\n *\n * _.isEmpty(null);\n * // => true\n *\n * _.isEmpty(true);\n * // => true\n *\n * _.isEmpty(1);\n * // => true\n *\n * _.isEmpty([1, 2, 3]);\n * // => false\n *\n * _.isEmpty({ 'a': 1 });\n * // => false\n */\nfunction isEmpty(value) {\n  if (value == null) {\n    return true;\n  }\n  if (isArrayLike(value) &&\n      (isArray(value) || typeof value == 'string' || typeof value.splice == 'function' ||\n        isBuffer(value) || isTypedArray(value) || isArguments(value))) {\n    return !value.length;\n  }\n  var tag = getTag(value);\n  if (tag == mapTag || tag == setTag) {\n    return !value.size;\n  }\n  if (isPrototype(value)) {\n    return !baseKeys(value).length;\n  }\n  for (var key in value) {\n    if (hasOwnProperty.call(value, key)) {\n      return false;\n    }\n  }\n  return true;\n}\n\nmodule.exports = isEmpty;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/isEmpty.js\n ** module id = 87\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/isEmpty.js?");
           
/***/ },
/* 88 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var isPrototype = __webpack_require__(89),\n    nativeKeys = __webpack_require__(90);\n\n/** Used for built-in method references. */\nvar objectProto = Object.prototype;\n\n/** Used to check objects for own properties. */\nvar hasOwnProperty = objectProto.hasOwnProperty;\n\n/**\n * The base implementation of `_.keys` which doesn't treat sparse arrays as dense.\n *\n * @private\n * @param {Object} object The object to query.\n * @returns {Array} Returns the array of property names.\n */\nfunction baseKeys(object) {\n  if (!isPrototype(object)) {\n    return nativeKeys(object);\n  }\n  var result = [];\n  for (var key in Object(object)) {\n    if (hasOwnProperty.call(object, key) && key != 'constructor') {\n      result.push(key);\n    }\n  }\n  return result;\n}\n\nmodule.exports = baseKeys;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/_baseKeys.js\n ** module id = 88\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/_baseKeys.js?");
           
/***/ },
/* 89 */
/***/ function(module, exports) {
           
           eval("/** Used for built-in method references. */\nvar objectProto = Object.prototype;\n\n/**\n * Checks if `value` is likely a prototype object.\n *\n * @private\n * @param {*} value The value to check.\n * @returns {boolean} Returns `true` if `value` is a prototype, else `false`.\n */\nfunction isPrototype(value) {\n  var Ctor = value && value.constructor,\n      proto = (typeof Ctor == 'function' && Ctor.prototype) || objectProto;\n\n  return value === proto;\n}\n\nmodule.exports = isPrototype;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/_isPrototype.js\n ** module id = 89\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/_isPrototype.js?");
           
/***/ },
/* 90 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var overArg = __webpack_require__(91);\n\n/* Built-in method references for those with the same name as other `lodash` methods. */\nvar nativeKeys = overArg(Object.keys, Object);\n\nmodule.exports = nativeKeys;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/_nativeKeys.js\n ** module id = 90\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/_nativeKeys.js?");
           
/***/ },
/* 91 */
/***/ function(module, exports) {
           
           eval("/**\n * Creates a unary function that invokes `func` with its argument transformed.\n *\n * @private\n * @param {Function} func The function to wrap.\n * @param {Function} transform The argument transform.\n * @returns {Function} Returns the new function.\n */\nfunction overArg(func, transform) {\n  return function(arg) {\n    return func(transform(arg));\n  };\n}\n\nmodule.exports = overArg;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/_overArg.js\n ** module id = 91\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/_overArg.js?");
           
/***/ },
/* 92 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var DataView = __webpack_require__(93),\n    Map = __webpack_require__(100),\n    Promise = __webpack_require__(101),\n    Set = __webpack_require__(102),\n    WeakMap = __webpack_require__(103),\n    baseGetTag = __webpack_require__(76),\n    toSource = __webpack_require__(98);\n\n/** `Object#toString` result references. */\nvar mapTag = '[object Map]',\n    objectTag = '[object Object]',\n    promiseTag = '[object Promise]',\n    setTag = '[object Set]',\n    weakMapTag = '[object WeakMap]';\n\nvar dataViewTag = '[object DataView]';\n\n/** Used to detect maps, sets, and weakmaps. */\nvar dataViewCtorString = toSource(DataView),\n    mapCtorString = toSource(Map),\n    promiseCtorString = toSource(Promise),\n    setCtorString = toSource(Set),\n    weakMapCtorString = toSource(WeakMap);\n\n/**\n * Gets the `toStringTag` of `value`.\n *\n * @private\n * @param {*} value The value to query.\n * @returns {string} Returns the `toStringTag`.\n */\nvar getTag = baseGetTag;\n\n// Fallback for data views, maps, sets, and weak maps in IE 11 and promises in Node.js < 6.\nif ((DataView && getTag(new DataView(new ArrayBuffer(1))) != dataViewTag) ||\n    (Map && getTag(new Map) != mapTag) ||\n    (Promise && getTag(Promise.resolve()) != promiseTag) ||\n    (Set && getTag(new Set) != setTag) ||\n    (WeakMap && getTag(new WeakMap) != weakMapTag)) {\n  getTag = function(value) {\n    var result = baseGetTag(value),\n        Ctor = result == objectTag ? value.constructor : undefined,\n        ctorString = Ctor ? toSource(Ctor) : '';\n\n    if (ctorString) {\n      switch (ctorString) {\n        case dataViewCtorString: return dataViewTag;\n        case mapCtorString: return mapTag;\n        case promiseCtorString: return promiseTag;\n        case setCtorString: return setTag;\n        case weakMapCtorString: return weakMapTag;\n      }\n    }\n    return result;\n  };\n}\n\nmodule.exports = getTag;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/_getTag.js\n ** module id = 92\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/_getTag.js?");
           
/***/ },
/* 93 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var getNative = __webpack_require__(94),\n    root = __webpack_require__(78);\n\n/* Built-in method references that are verified to be native. */\nvar DataView = getNative(root, 'DataView');\n\nmodule.exports = DataView;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/_DataView.js\n ** module id = 93\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/_DataView.js?");
           
/***/ },
/* 94 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var baseIsNative = __webpack_require__(95),\n    getValue = __webpack_require__(99);\n\n/**\n * Gets the native function at `key` of `object`.\n *\n * @private\n * @param {Object} object The object to query.\n * @param {string} key The key of the method to get.\n * @returns {*} Returns the function if it's native, else `undefined`.\n */\nfunction getNative(object, key) {\n  var value = getValue(object, key);\n  return baseIsNative(value) ? value : undefined;\n}\n\nmodule.exports = getNative;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/_getNative.js\n ** module id = 94\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/_getNative.js?");
           
/***/ },
/* 95 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var isFunction = __webpack_require__(75),\n    isMasked = __webpack_require__(96),\n    isObject = __webpack_require__(82),\n    toSource = __webpack_require__(98);\n\n/**\n * Used to match `RegExp`\n * [syntax characters](http://ecma-international.org/ecma-262/7.0/#sec-patterns).\n */\nvar reRegExpChar = /[\\\\^$.*+?()[\\]{}|]/g;\n\n/** Used to detect host constructors (Safari). */\nvar reIsHostCtor = /^\\[object .+?Constructor\\]$/;\n\n/** Used for built-in method references. */\nvar funcProto = Function.prototype,\n    objectProto = Object.prototype;\n\n/** Used to resolve the decompiled source of functions. */\nvar funcToString = funcProto.toString;\n\n/** Used to check objects for own properties. */\nvar hasOwnProperty = objectProto.hasOwnProperty;\n\n/** Used to detect if a method is native. */\nvar reIsNative = RegExp('^' +\n  funcToString.call(hasOwnProperty).replace(reRegExpChar, '\\\\$&')\n  .replace(/hasOwnProperty|(function).*?(?=\\\\\\()| for .+?(?=\\\\\\])/g, '$1.*?') + '$'\n);\n\n/**\n * The base implementation of `_.isNative` without bad shim checks.\n *\n * @private\n * @param {*} value The value to check.\n * @returns {boolean} Returns `true` if `value` is a native function,\n *  else `false`.\n */\nfunction baseIsNative(value) {\n  if (!isObject(value) || isMasked(value)) {\n    return false;\n  }\n  var pattern = isFunction(value) ? reIsNative : reIsHostCtor;\n  return pattern.test(toSource(value));\n}\n\nmodule.exports = baseIsNative;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/_baseIsNative.js\n ** module id = 95\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/_baseIsNative.js?");
           
/***/ },
/* 96 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var coreJsData = __webpack_require__(97);\n\n/** Used to detect methods masquerading as native. */\nvar maskSrcKey = (function() {\n  var uid = /[^.]+$/.exec(coreJsData && coreJsData.keys && coreJsData.keys.IE_PROTO || '');\n  return uid ? ('Symbol(src)_1.' + uid) : '';\n}());\n\n/**\n * Checks if `func` has its source masked.\n *\n * @private\n * @param {Function} func The function to check.\n * @returns {boolean} Returns `true` if `func` is masked, else `false`.\n */\nfunction isMasked(func) {\n  return !!maskSrcKey && (maskSrcKey in func);\n}\n\nmodule.exports = isMasked;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/_isMasked.js\n ** module id = 96\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/_isMasked.js?");
           
/***/ },
/* 97 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var root = __webpack_require__(78);\n\n/** Used to detect overreaching core-js shims. */\nvar coreJsData = root['__core-js_shared__'];\n\nmodule.exports = coreJsData;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/_coreJsData.js\n ** module id = 97\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/_coreJsData.js?");
           
/***/ },
/* 98 */
/***/ function(module, exports) {
           
           eval("/** Used for built-in method references. */\nvar funcProto = Function.prototype;\n\n/** Used to resolve the decompiled source of functions. */\nvar funcToString = funcProto.toString;\n\n/**\n * Converts `func` to its source code.\n *\n * @private\n * @param {Function} func The function to convert.\n * @returns {string} Returns the source code.\n */\nfunction toSource(func) {\n  if (func != null) {\n    try {\n      return funcToString.call(func);\n    } catch (e) {}\n    try {\n      return (func + '');\n    } catch (e) {}\n  }\n  return '';\n}\n\nmodule.exports = toSource;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/_toSource.js\n ** module id = 98\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/_toSource.js?");
           
/***/ },
/* 99 */
/***/ function(module, exports) {
           
           eval("/**\n * Gets the value at `key` of `object`.\n *\n * @private\n * @param {Object} [object] The object to query.\n * @param {string} key The key of the property to get.\n * @returns {*} Returns the property value.\n */\nfunction getValue(object, key) {\n  return object == null ? undefined : object[key];\n}\n\nmodule.exports = getValue;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/_getValue.js\n ** module id = 99\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/_getValue.js?");
           
/***/ },
/* 100 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var getNative = __webpack_require__(94),\n    root = __webpack_require__(78);\n\n/* Built-in method references that are verified to be native. */\nvar Map = getNative(root, 'Map');\n\nmodule.exports = Map;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/_Map.js\n ** module id = 100\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/_Map.js?");
           
/***/ },
/* 101 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var getNative = __webpack_require__(94),\n    root = __webpack_require__(78);\n\n/* Built-in method references that are verified to be native. */\nvar Promise = getNative(root, 'Promise');\n\nmodule.exports = Promise;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/_Promise.js\n ** module id = 101\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/_Promise.js?");
           
/***/ },
/* 102 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var getNative = __webpack_require__(94),\n    root = __webpack_require__(78);\n\n/* Built-in method references that are verified to be native. */\nvar Set = getNative(root, 'Set');\n\nmodule.exports = Set;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/_Set.js\n ** module id = 102\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/_Set.js?");
           
/***/ },
/* 103 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var getNative = __webpack_require__(94),\n    root = __webpack_require__(78);\n\n/* Built-in method references that are verified to be native. */\nvar WeakMap = getNative(root, 'WeakMap');\n\nmodule.exports = WeakMap;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/_WeakMap.js\n ** module id = 103\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/_WeakMap.js?");
           
/***/ },
/* 104 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var baseIsArguments = __webpack_require__(105),\n    isObjectLike = __webpack_require__(106);\n\n/** Used for built-in method references. */\nvar objectProto = Object.prototype;\n\n/** Used to check objects for own properties. */\nvar hasOwnProperty = objectProto.hasOwnProperty;\n\n/** Built-in value references. */\nvar propertyIsEnumerable = objectProto.propertyIsEnumerable;\n\n/**\n * Checks if `value` is likely an `arguments` object.\n *\n * @static\n * @memberOf _\n * @since 0.1.0\n * @category Lang\n * @param {*} value The value to check.\n * @returns {boolean} Returns `true` if `value` is an `arguments` object,\n *  else `false`.\n * @example\n *\n * _.isArguments(function() { return arguments; }());\n * // => true\n *\n * _.isArguments([1, 2, 3]);\n * // => false\n */\nvar isArguments = baseIsArguments(function() { return arguments; }()) ? baseIsArguments : function(value) {\n  return isObjectLike(value) && hasOwnProperty.call(value, 'callee') &&\n    !propertyIsEnumerable.call(value, 'callee');\n};\n\nmodule.exports = isArguments;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/isArguments.js\n ** module id = 104\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/isArguments.js?");
           
/***/ },
/* 105 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var baseGetTag = __webpack_require__(76),\n    isObjectLike = __webpack_require__(106);\n\n/** `Object#toString` result references. */\nvar argsTag = '[object Arguments]';\n\n/**\n * The base implementation of `_.isArguments`.\n *\n * @private\n * @param {*} value The value to check.\n * @returns {boolean} Returns `true` if `value` is an `arguments` object,\n */\nfunction baseIsArguments(value) {\n  return isObjectLike(value) && baseGetTag(value) == argsTag;\n}\n\nmodule.exports = baseIsArguments;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/_baseIsArguments.js\n ** module id = 105\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/_baseIsArguments.js?");
           
/***/ },
/* 106 */
/***/ function(module, exports) {
           
           eval("/**\n * Checks if `value` is object-like. A value is object-like if it's not `null`\n * and has a `typeof` result of \"object\".\n *\n * @static\n * @memberOf _\n * @since 4.0.0\n * @category Lang\n * @param {*} value The value to check.\n * @returns {boolean} Returns `true` if `value` is object-like, else `false`.\n * @example\n *\n * _.isObjectLike({});\n * // => true\n *\n * _.isObjectLike([1, 2, 3]);\n * // => true\n *\n * _.isObjectLike(_.noop);\n * // => false\n *\n * _.isObjectLike(null);\n * // => false\n */\nfunction isObjectLike(value) {\n  return value != null && typeof value == 'object';\n}\n\nmodule.exports = isObjectLike;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/isObjectLike.js\n ** module id = 106\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/isObjectLike.js?");
           
/***/ },
/* 107 */
/***/ function(module, exports) {
           
           eval("/**\n * Checks if `value` is classified as an `Array` object.\n *\n * @static\n * @memberOf _\n * @since 0.1.0\n * @category Lang\n * @param {*} value The value to check.\n * @returns {boolean} Returns `true` if `value` is an array, else `false`.\n * @example\n *\n * _.isArray([1, 2, 3]);\n * // => true\n *\n * _.isArray(document.body.children);\n * // => false\n *\n * _.isArray('abc');\n * // => false\n *\n * _.isArray(_.noop);\n * // => false\n */\nvar isArray = Array.isArray;\n\nmodule.exports = isArray;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/isArray.js\n ** module id = 107\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/isArray.js?");
           
/***/ },
/* 108 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var isFunction = __webpack_require__(75),\n    isLength = __webpack_require__(109);\n\n/**\n * Checks if `value` is array-like. A value is considered array-like if it's\n * not a function and has a `value.length` that's an integer greater than or\n * equal to `0` and less than or equal to `Number.MAX_SAFE_INTEGER`.\n *\n * @static\n * @memberOf _\n * @since 4.0.0\n * @category Lang\n * @param {*} value The value to check.\n * @returns {boolean} Returns `true` if `value` is array-like, else `false`.\n * @example\n *\n * _.isArrayLike([1, 2, 3]);\n * // => true\n *\n * _.isArrayLike(document.body.children);\n * // => true\n *\n * _.isArrayLike('abc');\n * // => true\n *\n * _.isArrayLike(_.noop);\n * // => false\n */\nfunction isArrayLike(value) {\n  return value != null && isLength(value.length) && !isFunction(value);\n}\n\nmodule.exports = isArrayLike;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/isArrayLike.js\n ** module id = 108\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/isArrayLike.js?");
           
/***/ },
/* 109 */
/***/ function(module, exports) {
           
           eval("/** Used as references for various `Number` constants. */\nvar MAX_SAFE_INTEGER = 9007199254740991;\n\n/**\n * Checks if `value` is a valid array-like length.\n *\n * **Note:** This method is loosely based on\n * [`ToLength`](http://ecma-international.org/ecma-262/7.0/#sec-tolength).\n *\n * @static\n * @memberOf _\n * @since 4.0.0\n * @category Lang\n * @param {*} value The value to check.\n * @returns {boolean} Returns `true` if `value` is a valid length, else `false`.\n * @example\n *\n * _.isLength(3);\n * // => true\n *\n * _.isLength(Number.MIN_VALUE);\n * // => false\n *\n * _.isLength(Infinity);\n * // => false\n *\n * _.isLength('3');\n * // => false\n */\nfunction isLength(value) {\n  return typeof value == 'number' &&\n    value > -1 && value % 1 == 0 && value <= MAX_SAFE_INTEGER;\n}\n\nmodule.exports = isLength;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/isLength.js\n ** module id = 109\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/isLength.js?");
           
/***/ },
/* 110 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("/* WEBPACK VAR INJECTION */(function(module) {var root = __webpack_require__(78),\n    stubFalse = __webpack_require__(112);\n\n/** Detect free variable `exports`. */\nvar freeExports = typeof exports == 'object' && exports && !exports.nodeType && exports;\n\n/** Detect free variable `module`. */\nvar freeModule = freeExports && typeof module == 'object' && module && !module.nodeType && module;\n\n/** Detect the popular CommonJS extension `module.exports`. */\nvar moduleExports = freeModule && freeModule.exports === freeExports;\n\n/** Built-in value references. */\nvar Buffer = moduleExports ? root.Buffer : undefined;\n\n/* Built-in method references for those with the same name as other `lodash` methods. */\nvar nativeIsBuffer = Buffer ? Buffer.isBuffer : undefined;\n\n/**\n * Checks if `value` is a buffer.\n *\n * @static\n * @memberOf _\n * @since 4.3.0\n * @category Lang\n * @param {*} value The value to check.\n * @returns {boolean} Returns `true` if `value` is a buffer, else `false`.\n * @example\n *\n * _.isBuffer(new Buffer(2));\n * // => true\n *\n * _.isBuffer(new Uint8Array(2));\n * // => false\n */\nvar isBuffer = nativeIsBuffer || stubFalse;\n\nmodule.exports = isBuffer;\n\n/* WEBPACK VAR INJECTION */}.call(exports, __webpack_require__(111)(module)))\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/isBuffer.js\n ** module id = 110\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/isBuffer.js?");
           
/***/ },
/* 111 */
/***/ function(module, exports) {
           
           eval("module.exports = function(module) {\r\n\tif(!module.webpackPolyfill) {\r\n\t\tmodule.deprecate = function() {};\r\n\t\tmodule.paths = [];\r\n\t\t// module.parent = undefined by default\r\n\t\tmodule.children = [];\r\n\t\tmodule.webpackPolyfill = 1;\r\n\t}\r\n\treturn module;\r\n}\r\n\n\n/*****************\n ** WEBPACK FOOTER\n ** (webpack)/buildin/module.js\n ** module id = 111\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///(webpack)/buildin/module.js?");
           
/***/ },
/* 112 */
/***/ function(module, exports) {
           
           eval("/**\n * This method returns `false`.\n *\n * @static\n * @memberOf _\n * @since 4.13.0\n * @category Util\n * @returns {boolean} Returns `false`.\n * @example\n *\n * _.times(2, _.stubFalse);\n * // => [false, false]\n */\nfunction stubFalse() {\n  return false;\n}\n\nmodule.exports = stubFalse;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/stubFalse.js\n ** module id = 112\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/stubFalse.js?");
           
/***/ },
/* 113 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var baseIsTypedArray = __webpack_require__(114),\n    baseUnary = __webpack_require__(115),\n    nodeUtil = __webpack_require__(116);\n\n/* Node.js helper references. */\nvar nodeIsTypedArray = nodeUtil && nodeUtil.isTypedArray;\n\n/**\n * Checks if `value` is classified as a typed array.\n *\n * @static\n * @memberOf _\n * @since 3.0.0\n * @category Lang\n * @param {*} value The value to check.\n * @returns {boolean} Returns `true` if `value` is a typed array, else `false`.\n * @example\n *\n * _.isTypedArray(new Uint8Array);\n * // => true\n *\n * _.isTypedArray([]);\n * // => false\n */\nvar isTypedArray = nodeIsTypedArray ? baseUnary(nodeIsTypedArray) : baseIsTypedArray;\n\nmodule.exports = isTypedArray;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/isTypedArray.js\n ** module id = 113\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/isTypedArray.js?");
           
/***/ },
/* 114 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var baseGetTag = __webpack_require__(76),\n    isLength = __webpack_require__(109),\n    isObjectLike = __webpack_require__(106);\n\n/** `Object#toString` result references. */\nvar argsTag = '[object Arguments]',\n    arrayTag = '[object Array]',\n    boolTag = '[object Boolean]',\n    dateTag = '[object Date]',\n    errorTag = '[object Error]',\n    funcTag = '[object Function]',\n    mapTag = '[object Map]',\n    numberTag = '[object Number]',\n    objectTag = '[object Object]',\n    regexpTag = '[object RegExp]',\n    setTag = '[object Set]',\n    stringTag = '[object String]',\n    weakMapTag = '[object WeakMap]';\n\nvar arrayBufferTag = '[object ArrayBuffer]',\n    dataViewTag = '[object DataView]',\n    float32Tag = '[object Float32Array]',\n    float64Tag = '[object Float64Array]',\n    int8Tag = '[object Int8Array]',\n    int16Tag = '[object Int16Array]',\n    int32Tag = '[object Int32Array]',\n    uint8Tag = '[object Uint8Array]',\n    uint8ClampedTag = '[object Uint8ClampedArray]',\n    uint16Tag = '[object Uint16Array]',\n    uint32Tag = '[object Uint32Array]';\n\n/** Used to identify `toStringTag` values of typed arrays. */\nvar typedArrayTags = {};\ntypedArrayTags[float32Tag] = typedArrayTags[float64Tag] =\ntypedArrayTags[int8Tag] = typedArrayTags[int16Tag] =\ntypedArrayTags[int32Tag] = typedArrayTags[uint8Tag] =\ntypedArrayTags[uint8ClampedTag] = typedArrayTags[uint16Tag] =\ntypedArrayTags[uint32Tag] = true;\ntypedArrayTags[argsTag] = typedArrayTags[arrayTag] =\ntypedArrayTags[arrayBufferTag] = typedArrayTags[boolTag] =\ntypedArrayTags[dataViewTag] = typedArrayTags[dateTag] =\ntypedArrayTags[errorTag] = typedArrayTags[funcTag] =\ntypedArrayTags[mapTag] = typedArrayTags[numberTag] =\ntypedArrayTags[objectTag] = typedArrayTags[regexpTag] =\ntypedArrayTags[setTag] = typedArrayTags[stringTag] =\ntypedArrayTags[weakMapTag] = false;\n\n/**\n * The base implementation of `_.isTypedArray` without Node.js optimizations.\n *\n * @private\n * @param {*} value The value to check.\n * @returns {boolean} Returns `true` if `value` is a typed array, else `false`.\n */\nfunction baseIsTypedArray(value) {\n  return isObjectLike(value) &&\n    isLength(value.length) && !!typedArrayTags[baseGetTag(value)];\n}\n\nmodule.exports = baseIsTypedArray;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/_baseIsTypedArray.js\n ** module id = 114\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/_baseIsTypedArray.js?");
           
/***/ },
/* 115 */
/***/ function(module, exports) {
           
           eval("/**\n * The base implementation of `_.unary` without support for storing metadata.\n *\n * @private\n * @param {Function} func The function to cap arguments for.\n * @returns {Function} Returns the new capped function.\n */\nfunction baseUnary(func) {\n  return function(value) {\n    return func(value);\n  };\n}\n\nmodule.exports = baseUnary;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/_baseUnary.js\n ** module id = 115\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/_baseUnary.js?");
           
/***/ },
/* 116 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("/* WEBPACK VAR INJECTION */(function(module) {var freeGlobal = __webpack_require__(79);\n\n/** Detect free variable `exports`. */\nvar freeExports = typeof exports == 'object' && exports && !exports.nodeType && exports;\n\n/** Detect free variable `module`. */\nvar freeModule = freeExports && typeof module == 'object' && module && !module.nodeType && module;\n\n/** Detect the popular CommonJS extension `module.exports`. */\nvar moduleExports = freeModule && freeModule.exports === freeExports;\n\n/** Detect free variable `process` from Node.js. */\nvar freeProcess = moduleExports && freeGlobal.process;\n\n/** Used to access faster Node.js helpers. */\nvar nodeUtil = (function() {\n  try {\n    return freeProcess && freeProcess.binding && freeProcess.binding('util');\n  } catch (e) {}\n}());\n\nmodule.exports = nodeUtil;\n\n/* WEBPACK VAR INJECTION */}.call(exports, __webpack_require__(111)(module)))\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/_nodeUtil.js\n ** module id = 116\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/_nodeUtil.js?");
           
/***/ },
/* 117 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var baseGetTag = __webpack_require__(76),\n    getPrototype = __webpack_require__(118),\n    isObjectLike = __webpack_require__(106);\n\n/** `Object#toString` result references. */\nvar objectTag = '[object Object]';\n\n/** Used for built-in method references. */\nvar funcProto = Function.prototype,\n    objectProto = Object.prototype;\n\n/** Used to resolve the decompiled source of functions. */\nvar funcToString = funcProto.toString;\n\n/** Used to check objects for own properties. */\nvar hasOwnProperty = objectProto.hasOwnProperty;\n\n/** Used to infer the `Object` constructor. */\nvar objectCtorString = funcToString.call(Object);\n\n/**\n * Checks if `value` is a plain object, that is, an object created by the\n * `Object` constructor or one with a `[[Prototype]]` of `null`.\n *\n * @static\n * @memberOf _\n * @since 0.8.0\n * @category Lang\n * @param {*} value The value to check.\n * @returns {boolean} Returns `true` if `value` is a plain object, else `false`.\n * @example\n *\n * function Foo() {\n *   this.a = 1;\n * }\n *\n * _.isPlainObject(new Foo);\n * // => false\n *\n * _.isPlainObject([1, 2, 3]);\n * // => false\n *\n * _.isPlainObject({ 'x': 0, 'y': 0 });\n * // => true\n *\n * _.isPlainObject(Object.create(null));\n * // => true\n */\nfunction isPlainObject(value) {\n  if (!isObjectLike(value) || baseGetTag(value) != objectTag) {\n    return false;\n  }\n  var proto = getPrototype(value);\n  if (proto === null) {\n    return true;\n  }\n  var Ctor = hasOwnProperty.call(proto, 'constructor') && proto.constructor;\n  return typeof Ctor == 'function' && Ctor instanceof Ctor &&\n    funcToString.call(Ctor) == objectCtorString;\n}\n\nmodule.exports = isPlainObject;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/isPlainObject.js\n ** module id = 117\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/isPlainObject.js?");
           
/***/ },
/* 118 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("var overArg = __webpack_require__(91);\n\n/** Built-in value references. */\nvar getPrototype = overArg(Object.getPrototypeOf, Object);\n\nmodule.exports = getPrototype;\n\n\n/*****************\n ** WEBPACK FOOTER\n ** ./~/_lodash@4.17.4@lodash/_getPrototype.js\n ** module id = 118\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./~/_lodash@4.17.4@lodash/_getPrototype.js?");
           
/***/ },
/* 119 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("'use strict';\n\nvar _promise = __webpack_require__(2);\n\nvar _promise2 = _interopRequireDefault(_promise);\n\nvar _create = __webpack_require__(72);\n\nvar _create2 = _interopRequireDefault(_create);\n\nfunction _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }\n\n/**\n * @Author: songqi\n * @Date:   2017-01-11\n * @Last modified by:   songqi\n * @Last modified time: 2017-05-08\n */\n\nvar bmAxios = weex.requireModule('bmAxios'),\n    Axios = (0, _create2.default)(null);\n\nAxios.install = function (Vue, options) {\n    Vue.prototype.$fetch = function (options) {\n        console.log(Vue.prototype.eros.apis, 11111);\n        // 不仅支持 success, error 的回调写法，还支持 promise 的写法\n        return new _promise2.default(function (resolve, reject) {\n            bmAxios.fetch({\n                method: options.method || 'GET',\n                url: Vue.prototype.eros.apis[options.name] || options.url,\n                header: options.header || {},\n                data: options.data || {}\n            }, function (resData) {\n                // 可以做统一的监控\n                Vue.prototype.eros.responseHandler(options, resData, resolve, reject);\n            });\n        });\n    };\n};\n\nVue.use(Axios);\n\n/*****************\n ** WEBPACK FOOTER\n ** ./src/js/widget/axios.js\n ** module id = 119\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./src/js/widget/axios.js?");
           
/***/ },
/* 120 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("'use strict';\n\nvar _promise = __webpack_require__(2);\n\nvar _promise2 = _interopRequireDefault(_promise);\n\nvar _create = __webpack_require__(72);\n\nvar _create2 = _interopRequireDefault(_create);\n\nvar _isFunction = __webpack_require__(75);\n\nvar _isFunction2 = _interopRequireDefault(_isFunction);\n\nfunction _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }\n\nvar modal = weex.requireModule('bmModal'),\n    geolocation = weex.requireModule('bmGeolocation');\n\nvar Geolocation = (0, _create2.default)(null);\n\nGeolocation.install = function (Vue) {\n    Vue.prototype.$geo = {\n        get: function get(callback) {\n            var _this = this;\n\n            return new _promise2.default(function (resolve, reject) {\n                geolocation.getGeolocation(function (resData) {\n                    if (!resData) {\n                        resData = {\n                            resCode: -1,\n                            msg: '获取信息失败，请重试',\n                            data: {}\n                        };\n                    }\n                    if ((0, _isFunction2.default)(callback)) {\n                        callback.call(_this, resData);\n                    }\n                    if (resData && resData.resCode == 0) {\n                        resolve(resData);\n                    } else {\n                        resData.msg && modal.alert({\n                            message: resData.msg,\n                            okTitle: '确定'\n                        });\n                        reject(resData);\n                    }\n                });\n            });\n        }\n    };\n};\n\nVue.use(Geolocation);\n\n/*****************\n ** WEBPACK FOOTER\n ** ./src/js/widget/geo.js\n ** module id = 120\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./src/js/widget/geo.js?");
           
/***/ },
/* 121 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("'use strict';\n\nvar _promise = __webpack_require__(2);\n\nvar _promise2 = _interopRequireDefault(_promise);\n\nvar _create = __webpack_require__(72);\n\nvar _create2 = _interopRequireDefault(_create);\n\nvar _isFunction = __webpack_require__(75);\n\nvar _isFunction2 = _interopRequireDefault(_isFunction);\n\nfunction _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }\n\n/**\n* @Author: songqi\n* @Date:   2017-01-11\n* @Last modified by:   songqi\n* @Last modified time: 2017-03-08\n*/\n\nvar camera = weex.requireModule('bmCamera'),\n    browser = weex.requireModule('bmBrowserImg'),\n    modal = weex.requireModule('bmModal');\n\nvar Camera = (0, _create2.default)(null);\n\nCamera.install = function (Vue, options) {\n    Vue.prototype.$camera = {\n        // 扫一扫\n        scanCode: function scanCode(callback) {\n            var _this = this;\n\n            return new _promise2.default(function (resolve, reject) {\n                camera.scan(function (resData) {\n                    if (!resData) {\n                        resData = {\n                            resCode: -1,\n                            msg: '获取信息失败，请重试',\n                            data: {}\n                        };\n                    }\n                    if ((0, _isFunction2.default)(callback)) {\n                        callback.call(_this, resData);\n                    }\n                    if (resData && resData.resCode == 0) {\n                        resolve(resData);\n                    } else {\n                        resData.msg && modal.alert({\n                            message: resData.msg,\n                            okTitle: '确定'\n                        });\n                        reject(resData);\n                    }\n                });\n            });\n        },\n\n        // 上传图片\n        uploadImg: function uploadImg(options, callback) {\n            var _this2 = this;\n\n            var options = options || {};\n            if ((0, _isFunction2.default)(options) && !callback) {\n                callback = options;\n                options = {};\n            }\n            return new _promise2.default(function (resolve, reject) {\n                camera.uploadImage({\n                    maxCount: options.maxCount || 1,\n                    imageWidth: options.imageWidth || 0,\n                    allowCrop: options.allowCrop || false\n                }, function (resData) {\n                    if (!resData) {\n                        resData = {\n                            resCode: -1,\n                            msg: '获取信息失败，请重试',\n                            data: {}\n                        };\n                    }\n                    if ((0, _isFunction2.default)(callback)) {\n                        callback.call(_this2, resData);\n                    }\n                    if (resData && resData.resCode == 0 && resData.data && resData.data && resData.data.length) {\n                        resolve(resData);\n                    } else {\n                        resData.msg && modal.alert({\n                            message: resData.msg || '上传图片失败',\n                            okTitle: '确定'\n                        });\n                        reject(resData);\n                    }\n                });\n            });\n        },\n\n        // 浏览图片\n        browserImg: function browserImg(options, callback) {\n            var options = options || {};\n            if ((0, _isFunction2.default)(options) && !callback) {\n                callback = options;\n                options = {};\n            }\n\n            return new _promise2.default(function (resolve, reject) {\n                browser.open({\n                    index: options.index,\n                    images: options.images,\n                    type: 'network'\n                }, function (resData) {\n                    // console.log(resData)\n                });\n            });\n        }\n    };\n};\n\nVue.use(Camera);\n\n/*****************\n ** WEBPACK FOOTER\n ** ./src/js/widget/camera.js\n ** module id = 121\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./src/js/widget/camera.js?");
           
/***/ },
/* 122 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("'use strict';\n\nvar _create = __webpack_require__(72);\n\nvar _create2 = _interopRequireDefault(_create);\n\nvar _isFunction = __webpack_require__(75);\n\nvar _isFunction2 = _interopRequireDefault(_isFunction);\n\nfunction _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }\n\nvar navigator = weex.requireModule('bmNavigator'),\n    modal = weex.requireModule('bmModal');\n\nvar Navigator = (0, _create2.default)(null);\n\nNavigator.install = function (Vue, options) {\n    Vue.prototype.$nav = {\n        setLeftItem: function setLeftItem(options, callback) {\n            navigator.setLeftItem(options, function () {\n                (0, _isFunction2.default)(callback) && callback();\n            });\n        },\n        setRightItem: function setRightItem(options, callback) {\n            navigator.setRightItem(options, function () {\n                (0, _isFunction2.default)(callback) && callback();\n            });\n        },\n        setCenterItem: function setCenterItem(options, callback) {\n            navigator.setCenterItem(options, function () {\n                (0, _isFunction2.default)(callback) && callback();\n            });\n        },\n        setNavigationInfo: function setNavigationInfo(options, callback) {\n            navigator.setNavigationInfo(options, function () {\n                (0, _isFunction2.default)(callback) && callback();\n            });\n        }\n    };\n};\n\nVue.use(Navigator);\n\n/*****************\n ** WEBPACK FOOTER\n ** ./src/js/widget/nav.js\n ** module id = 122\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./src/js/widget/nav.js?");
           
/***/ },
/* 123 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("'use strict';\n\nvar _promise = __webpack_require__(2);\n\nvar _promise2 = _interopRequireDefault(_promise);\n\nvar _create = __webpack_require__(72);\n\nvar _create2 = _interopRequireDefault(_create);\n\nvar _isFunction = __webpack_require__(75);\n\nvar _isFunction2 = _interopRequireDefault(_isFunction);\n\nfunction _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }\n\n/**\n* @Author: songqi\n* @Date:   2017-01-11\n* @Last modified by:   songqi\n* @Last modified time: 2017-02-09\n*/\n\nvar pay = weex.requireModule('bmPay'),\n    modal = weex.requireModule('bmModal');\n\nvar Pay = (0, _create2.default)(null);\n\nPay.install = function (Vue, options) {\n    Vue.prototype.$wechatPay = {\n        wechat: function wechat(params, callback) {\n            var _this = this;\n\n            return new _promise2.default(function (resolve, reject) {\n                pay.payByWechat(params, function (resData) {\n                    if ((0, _isFunction2.default)(callback)) {\n                        callback.call(_this, resData);\n                    }\n                    if (resData && resData.resCode == 0) {\n                        resolve(resData);\n                    } else {\n                        reject(resData);\n                    }\n                });\n            });\n        }\n    };\n};\n\nVue.use(Pay);\n\n/*****************\n ** WEBPACK FOOTER\n ** ./src/js/widget/pay.js\n ** module id = 123\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./src/js/widget/pay.js?");
           
/***/ },
/* 124 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("'use strict';\n\nvar _promise = __webpack_require__(2);\n\nvar _promise2 = _interopRequireDefault(_promise);\n\nvar _stringify = __webpack_require__(85);\n\nvar _stringify2 = _interopRequireDefault(_stringify);\n\nvar _create = __webpack_require__(72);\n\nvar _create2 = _interopRequireDefault(_create);\n\nvar _isEmpty = __webpack_require__(87);\n\nvar _isEmpty2 = _interopRequireDefault(_isEmpty);\n\nvar _isFunction = __webpack_require__(75);\n\nvar _isFunction2 = _interopRequireDefault(_isFunction);\n\nfunction _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }\n\n/**\n* @Author: songqi\n* @Date:   2017-01-11\n* @Last modified by:   songqi\n* @Last modified time: 2017-02-10\n*/\n\nvar storage = weex.requireModule('bmStorage'),\n    modal = weex.requireModule('bmModal');\n\nvar Storage = (0, _create2.default)(null);\n\nStorage.install = function (Vue, options) {\n    Vue.prototype.$storage = {\n        set: function set(key, value, callback) {\n            var _this = this;\n\n            if (key === 'userInfo') {\n                value = (0, _stringify2.default)(value);\n            } else {\n                value = (0, _stringify2.default)({\n                    data: value\n                });\n            }\n            return new _promise2.default(function (resolve, reject) {\n                storage.setData(key, value, function (resData) {\n                    if ((0, _isFunction2.default)(callback)) {\n                        callback.call(_this, resData);\n                    }\n                    if (resData && resData.resCode == 0) {\n                        resolve(resData);\n                    } else {\n                        reject(resData);\n                    }\n                });\n            });\n        },\n        get: function get(key, callback) {\n            var _this2 = this;\n\n            return new _promise2.default(function (resolve, reject) {\n                storage.getData(key, function (resData) {\n                    resData.data = JSON.parse(resData.data.value || '{}');\n                    if (key !== 'userInfo') {\n                        resData.data = resData.data.data;\n                    }\n                    if ((0, _isFunction2.default)(callback)) {\n                        callback.call(_this2, resData);\n                    }\n                    if (resData && resData.resCode == 0) {\n                        resolve(resData.data);\n                    } else if (resData && resData.resCode == 9) {\n                        // 如果发现值不存在则返回空字符串\n                        resolve('');\n                    } else {\n                        reject(resData);\n                    }\n                });\n            });\n        },\n        delete: function _delete(key, callback) {\n            var _this3 = this;\n\n            return new _promise2.default(function (resolve, reject) {\n                storage.deleteData(key, function (resData) {\n                    if ((0, _isFunction2.default)(callback)) {\n                        callback.call(_this3, resData);\n                    }\n                    if (resData && resData.resCode == 0) {\n                        resolve(resData);\n                    } else {\n                        reject(resData);\n                    }\n                });\n            });\n        },\n        remove: function remove(callback) {\n            var _this4 = this;\n\n            return new _promise2.default(function (resolve, reject) {\n                storage.removeData(function (resData) {\n                    if ((0, _isFunction2.default)(callback)) {\n                        callback.call(_this4, resData);\n                    }\n                    if (resData && resData.resCode == 0) {\n                        resolve(resData);\n                    } else {\n                        reject(resData);\n                    }\n                });\n            });\n        }\n    };\n};\n\nVue.use(Storage);\n\n/*****************\n ** WEBPACK FOOTER\n ** ./src/js/widget/storage.js\n ** module id = 124\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./src/js/widget/storage.js?");
           
/***/ },
/* 125 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("'use strict';\n\nvar _create = __webpack_require__(72);\n\nvar _create2 = _interopRequireDefault(_create);\n\nvar _isArray2 = __webpack_require__(107);\n\nvar _isArray3 = _interopRequireDefault(_isArray2);\n\nvar _message = __webpack_require__(126);\n\nfunction _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }\n\n/**\n* @Author: songqi\n* @Date:   2017-02-27\n* @Last modified by:   songqi\n* @Last modified time: 2017-04-10\n*/\n\nvar events = weex.requireModule('bmEvents'),\n    globalEvent = weex.requireModule('globalEvent');\n\nvar GlobalEvent = (0, _create2.default)(null);\nvar GLOBALEVENT = (0, _create2.default)(null);\n\n// 消息推送\nglobalEvent.addEventListener(\"pushMessage\", function (options) {\n    (0, _message.distribute)(options, GLOBALEVENT);\n});\n\n// app 被放到后台\nglobalEvent.addEventListener(\"appWillResignActive\", function (options) {\n    (0, _isArray3.default)(GLOBALEVENT['appDeActive']) && GLOBALEVENT['appDeActive'].map(function (item) {\n        item(options);\n    });\n});\n\n// app 从后台唤起\nglobalEvent.addEventListener(\"appDidBecomeActive\", function (options) {\n    (0, _isArray3.default)(GLOBALEVENT['appActive']) && GLOBALEVENT['appActive'].map(function (item) {\n        item(options);\n    });\n});\n\nGlobalEvent.install = function (Vue, options) {\n    Vue.mixin({\n        beforeCreate: function beforeCreate() {\n            if (this.$options.bmGlobalEvent) {\n                var ev = this.$options.bmGlobalEvent;\n                for (var i in ev) {\n                    if (!GLOBALEVENT[i]) {\n                        GLOBALEVENT[i] = [];\n                    }\n                    GLOBALEVENT[i].push(ev[i].bind(this));\n                }\n            }\n        }\n    });\n    Vue.prototype.$bus = events;\n};\n\nVue.use(GlobalEvent);\n\n/*****************\n ** WEBPACK FOOTER\n ** ./src/js/widget/events.js\n ** module id = 125\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./src/js/widget/events.js?");
           
/***/ },
/* 126 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("'use strict';\n\nObject.defineProperty(exports, \"__esModule\", {\n    value: true\n});\nexports.distribute = undefined;\n\nvar _isArray2 = __webpack_require__(107);\n\nvar _isArray3 = _interopRequireDefault(_isArray2);\n\nfunction _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }\n\nvar router = weex.requireModule('bmRouter');\n\nvar MESSAGE_MAP = {\n    gongdan: function gongdan() {\n        var pageCfg = this.eros.pages['worderCount'];\n        router.open({\n            url: pageCfg.url,\n            animateType: 'PUSH',\n            params: {},\n            authorize: true,\n            navigationInfo: {\n                hideNavbar: false,\n                title: pageCfg.title\n            }\n        });\n    }\n};\n\n// 事件分发器\nvar distribute = exports.distribute = function distribute(options, eventHandle) {\n    MESSAGE_MAP[options.type] && MESSAGE_MAP[options.type](options);\n};\n\n// 用户消息推送\nvar commonMsg = function commonMsg(options, eventHandle) {\n    var pageCfg = getPageUrl('message_lists'),\n        nowPage = weex.config.bundleUrl;\n    if (options.trigger && nowPage.indexOf(pageCfg.url) === -1) {\n        router.open({\n            url: pageCfg.url,\n            animateType: 'PUSH',\n            params: {},\n            authorize: true,\n            navigationInfo: {\n                hideNavbar: false,\n                title: pageCfg.title\n            }\n        });\n    } else {\n        (0, _isArray3.default)(eventHandle['pushMessage']) && eventHandle['pushMessage'].map(function (item) {\n            item(options);\n        });\n    }\n};\n\n/*****************\n ** WEBPACK FOOTER\n ** ./src/js/utils/message.js\n ** module id = 126\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./src/js/utils/message.js?");
           
/***/ },
/* 127 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("'use strict';\n\nvar _promise = __webpack_require__(2);\n\nvar _promise2 = _interopRequireDefault(_promise);\n\nvar _create = __webpack_require__(72);\n\nvar _create2 = _interopRequireDefault(_create);\n\nfunction _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }\n\nvar _share = weex.requireModule('bmShare');\n\nvar Share = (0, _create2.default)(null);\n\nShare.install = function (Vue, options) {\n    Vue.prototype.$share = {\n        share: function share(cfg) {\n            return new _promise2.default(function (resolve, reject) {\n                _share.share({\n                    title: cfg.title,\n                    content: cfg.content || '',\n                    image: cfg.image || '',\n                    url: cfg.url || '',\n                    platforms: cfg.platforms || [] // 传空的话默认全部\n\n                }, function (data) {\n                    resolve(data);\n                }, function (err) {\n                    reject(err);\n                });\n            });\n        }\n    };\n};\n\nVue.use(Share);\n\n/*****************\n ** WEBPACK FOOTER\n ** ./src/js/widget/share.js\n ** module id = 127\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./src/js/widget/share.js?");
           
/***/ },
/* 128 */
/***/ function(module, exports, __webpack_require__) {
           
           eval("'use strict';\n\nvar _promise = __webpack_require__(2);\n\nvar _promise2 = _interopRequireDefault(_promise);\n\nvar _create = __webpack_require__(72);\n\nvar _create2 = _interopRequireDefault(_create);\n\nvar _isFunction = __webpack_require__(75);\n\nvar _isFunction2 = _interopRequireDefault(_isFunction);\n\nfunction _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }\n\nvar tools = weex.requireModule('bmTool'); /**\n                                           * @Author: songqi\n                                           * @Date:   2017-05-05\n                                           * @Last modified by:   songqi\n                                           * @Last modified time: 2017-05-08\n                                           */\n\nvar Tools = (0, _create2.default)(null);\n\nTools.install = function (Vue, options) {\n    Vue.prototype.$tools = {\n        // 收起键盘\n        resignKeyboard: function resignKeyboard() {\n            return new _promise2.default(function (resolve, reject) {\n                tools.resignKeyboard(function (resData) {\n                    if (resData && resData.resCode == 0) {\n                        resolve(resData);\n                    } else {\n                        reject(resData);\n                    }\n                });\n            });\n        },\n\n\n        // 是否安装微信\n        isInstallWXApp: function isInstallWXApp(options) {\n            var _this = this;\n\n            options = options || {};\n            return new _promise2.default(function (resolve, reject) {\n                tools.isInstallWXApp(function (resData) {\n                    if ((0, _isFunction2.default)(options.callback)) {\n                        options.callback.call(_this);\n                    }\n                    if (resData && resData.resCode == 0) {\n                        resolve(resData);\n                    } else {\n                        reject(resData);\n                    }\n                });\n            });\n        },\n\n\n        // 获取 cid\n        getCid: function getCid(options) {\n            var _this2 = this;\n\n            options = options || {};\n            return new _promise2.default(function (resolve, reject) {\n                tools.getCid(function (resData) {\n                    if ((0, _isFunction2.default)(options.callback)) {\n                        options.callback.call(_this2);\n                    }\n                    if (resData && resData.resCode == 0) {\n                        resolve(resData);\n                    } else {\n                        reject(resData);\n                    }\n                });\n            });\n        },\n\n\n        // 复制内容到剪切板\n        copyString: function copyString(string, callback) {\n            var _this3 = this;\n\n            return new _promise2.default(function (resolve, reject) {\n                tools.copyString(string, function (resData) {\n                    if ((0, _isFunction2.default)(callback)) {\n                        callback.call(_this3);\n                    }\n                    if (resData && resData.resCode == 0) {\n                        resolve(resData);\n                    } else {\n                        reject(resData);\n                    }\n                });\n            });\n        }\n    };\n};\n\nVue.use(Tools);\n\n/*****************\n ** WEBPACK FOOTER\n ** ./src/js/widget/tools.js\n ** module id = 128\n ** module chunks = 0\n **/\n//# sourceURL=webpack:///./src/js/widget/tools.js?");
           
/***/ }
/******/ ]);
